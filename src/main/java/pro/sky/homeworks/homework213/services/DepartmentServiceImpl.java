package pro.sky.homeworks.homework213.services;

import org.springframework.stereotype.Service;
import pro.sky.homeworks.homework213.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;


    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee haveMaxSalaryInDept(int departmentId) {
        return employeeService.getMap().values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary)).orElseThrow(IllegalStateException::new);
    }


    @Override
    public Employee haveMinSalaryInDept(int departmentId) {
        return employeeService.getMap().values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary)).orElseThrow();
    }

    @Override
    public List<Employee> getAllByDept(int departmentId) {
        return employeeService.getMap().values()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
        return employeeService.getMap().values()
                .stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
