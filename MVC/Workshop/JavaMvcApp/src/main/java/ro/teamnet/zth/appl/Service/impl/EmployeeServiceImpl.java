package ro.teamnet.zth.appl.Service.impl;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.Service.EmployeeService;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyService
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> getAllEmployees() {
        return new EmployeeDao().getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Long id) {
        return new EmployeeDao().getEmployeeById(id);
    }

    @Override
    public Employee deleteEmployee(Long id) {
        EmployeeDao empDao=new EmployeeDao();

        Employee employee=empDao.getEmployeeById(id);
        empDao.deleteEmployee(employee);

        return employee;
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        return new EmployeeDao().insertEmployee(employee);
    }
}
