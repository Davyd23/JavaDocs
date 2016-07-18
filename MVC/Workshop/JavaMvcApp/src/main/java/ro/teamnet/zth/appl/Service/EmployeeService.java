package ro.teamnet.zth.appl.Service;

import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Employee findOneEmployee(Long id);

    public Employee deleteEmployee(Long id);

    public Employee insertEmployee(Employee employee);
}
