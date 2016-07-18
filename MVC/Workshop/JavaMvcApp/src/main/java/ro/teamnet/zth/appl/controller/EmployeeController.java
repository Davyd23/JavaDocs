package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.InjectService;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.Service.EmployeeService;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @InjectService
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @MyRequestMethod(urlPath = "/one")
    public Object getOneEmployee(@MyRequestParam(name="id")Long id){
        return employeeService.findOneEmployee(id);
    }

    @MyRequestMethod(urlPath = "/delete")
    public Object deleteEmployee(@MyRequestParam(name="id") Long id){
        return employeeService.deleteEmployee(id);
    }

    @MyRequestMethod(urlPath = "/insert")
    public Object insertEmployee(@MyRequestParam(name="id") Long id,@MyRequestParam(name="firstName") String firstName,
                                 @MyRequestParam(name="lastName") String lastName){
        Employee employee=new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return employeeService.insertEmployee(employee);
    }
}
