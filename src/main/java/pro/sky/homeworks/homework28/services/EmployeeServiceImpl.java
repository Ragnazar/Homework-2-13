package pro.sky.homeworks.homework28.services;

import org.springframework.stereotype.Service;
import pro.sky.homeworks.homework27.exceptions.EmployeeAlreadyAddedException;
import pro.sky.homeworks.homework27.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public HashMap<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Работник уже есть в списке");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Работник отсутствует");
        }
        employees.remove(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Работник отсутствует");
        }
        return employees.get(employee.getFullName());
    }

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
