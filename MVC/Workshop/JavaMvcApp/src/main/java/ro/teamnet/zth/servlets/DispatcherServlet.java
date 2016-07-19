package ro.teamnet.zth.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.util.BufferRecycler;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyObject;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by user on 7/14/2016.
 */
public class DispatcherServlet extends HttpServlet{
    /*rol de registru
    key:urlPath
    val: informatii despre metoda care va procesa acest url*/
    HashMap<String,MethodAttributes> allowedMethods=
            new HashMap<String,MethodAttributes>();
    @Override
    public void init() throws ServletException {
        try {
            //cautam clase din pachet
            Iterable<Class> controllers= AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for(Class controller:controllers){
               if( controller.isAnnotationPresent(MyController.class)){
                   MyController myControllerAnnotation=
                           (MyController) controller.getAnnotation(MyController.class);
                   String controllerUrlPah=myControllerAnnotation.urlPath();
                   Method[] controllerMethods=controller.getMethods();

                   for(Method controllerMethod:controllerMethods){
                       if(controllerMethod.isAnnotationPresent(MyRequestMethod.class)){
                           MyRequestMethod myRequestMethod=
                                   controllerMethod.getAnnotation(MyRequestMethod.class);

                           String methodUrlPath=myRequestMethod.urlPath();
                           //construiesc  cheia pt hashMap
                           String urlPath=controllerUrlPah+methodUrlPath;

                           //construiesc valoarea
                           MethodAttributes methodAttributes=new MethodAttributes();
                           methodAttributes.setControllerClass(controller.getName());
                           methodAttributes.setMethodName(controllerMethod.getName());
                           methodAttributes.setMethodType(myRequestMethod.methodType());
                           methodAttributes.setParameterTypes(controllerMethod.getParameterTypes());

                           allowedMethods.put(urlPath,methodAttributes);

                       }
                   }
               }
            }
        } catch (ClassNotFoundException |IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST",req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("DELETE",req,resp);
    }

    private void dispatchReply(String identificator, HttpServletRequest req, HttpServletResponse resp){
        Object response=null;
        try {
            response = dispatch(req, resp);
        }catch (Exception ex){
            sendExceptionError(ex,req,resp);
        }

        try {
            reply(response,req,resp);
        } catch (IOException e) {
            sendExceptionError(e,req,resp);
        }
    }

    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {

        String path=req.getPathInfo(); // it gets everything after mvc ( from )

        MethodAttributes methodAttributes=allowedMethods.get(path);

        if(methodAttributes==null)return "Hello";//nu putem procesa acest url

        String controllerName=methodAttributes.getControllerClass();
        Class<?> controllerClass=Class.forName(controllerName);
        Object instance=controllerClass.newInstance();

        Method method=controllerClass.getMethod(methodAttributes.getMethodName(),
                methodAttributes.getParameterTypes());

        Parameter[] parameters=method.getParameters();
        List<Object> parameterValues=new ArrayList<>();

        for(Parameter parameter:parameters){
            if(parameter.isAnnotationPresent(MyRequestParam.class)){

                MyRequestParam annotation =parameter.getAnnotation(MyRequestParam.class);
                String name=annotation.name();
                String requestParamValue=req.getParameter(name);

                Class<?> type = parameter.getType();//tipul de date pe care il introducem
                Object requestParamObject=new ObjectMapper().readValue(requestParamValue,type); //it converts from string to data type
                parameterValues.add(requestParamObject);

            }else if(parameter.isAnnotationPresent(MyObject.class)){

                BufferedReader br=req.getReader();
                Object requestParamObject=new ObjectMapper().readValue(br,parameter.getType());

                parameterValues.add(requestParamObject);
            }
        }

        return method.invoke(instance,parameterValues.toArray());
    }

    private void reply(Object response,HttpServletRequest req, HttpServletResponse resp)throws IOException{
        ObjectMapper mapper=new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(response));
    }

    private void sendExceptionError(Exception ex,HttpServletRequest req, HttpServletResponse resp){

    }
}
