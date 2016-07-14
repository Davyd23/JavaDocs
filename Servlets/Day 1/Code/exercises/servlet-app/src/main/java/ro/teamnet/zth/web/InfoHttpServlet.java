package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by user on 7/12/2016.
 */
public class InfoHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder sb=new StringBuilder();
        sb.append("<table>");

        Enumeration<String> headers=request.getHeaderNames();

        while(headers.hasMoreElements()){
            String headerName=headers.nextElement();
            sb.append("<tr><th>"+headerName+"</th><th>"+request.getHeader(headerName)+"</th><tr>");
        }
        sb.append("</table>");

        // Set the response type
        response.setContentType("text/html");


       response.getWriter().write(sb.toString());
    }

}
