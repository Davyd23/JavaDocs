package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.Service.impl.DepartmentServiceImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/departments")
public class DepartmentController  {

    @MyRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartments(){
        return new DepartmentServiceImpl().getAllDepartments();
    }
}
