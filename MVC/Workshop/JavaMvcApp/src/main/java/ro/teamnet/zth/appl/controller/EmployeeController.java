package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.Service.EmployeeService;
import ro.teamnet.zth.appl.Service.impl.EmployeeServiceImpl;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {

    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees(){
        return new EmployeeServiceImpl().getAllEmployees();
    }

    @MyRequestMethod(urlPath = "/one")
    public Object getOneEmployee(@MyRequestParam(name="id")Long id){
        return new EmployeeServiceImpl().findOneEmployee(id);
    }

    @MyRequestMethod(urlPath = "/delete")
    public Object deleteEmployee(@MyRequestParam(name="id") Long id){
        return new EmployeeServiceImpl().deleteEmployee(id);
    }

    @MyRequestMethod(urlPath = "/insert")
    public Object insertEmployee(@MyRequestParam(name="id") Long id,@MyRequestParam(name="firstName") String firstName,
                                 @MyRequestParam(name="lastName") String lastName){
        Employee employee=new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return new EmployeeServiceImpl().insertEmployee(employee);
    }
}
