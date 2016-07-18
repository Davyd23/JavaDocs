package ro.teamnet.zth.appl.Service.impl;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.Service.DepartmentService;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyService
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public List<Department> getAllDepartments() {
        return new DepartmentDao().findAllDepartments();
    }
}
