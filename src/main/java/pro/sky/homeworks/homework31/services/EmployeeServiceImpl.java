package pro.sky.homeworks.homework31.services;

import org.springframework.stereotype.Service;
import pro.sky.homeworks.homework31.exceptions.EmployeeAlreadyAddedException;
import pro.sky.homeworks.homework31.exceptions.EmployeeNotFoundException;
import pro.sky.homeworks.homework31.Employee;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>(Map.of(
            getKey(new Employee("Ivanov", "Ivan", 1, Math.ceil(Math.random() * 100000))),
            new Employee("Ivanov", "Ivan", 1, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Sidorov", "Ivan", 2, Math.ceil(Math.random() * 100000))),
            new Employee("Sidorov", "Ivan", 2, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Petrov", "Ivan", 5, Math.ceil(Math.random() * 100000))),
            new Employee("Petrov", "Ivan", 5, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Miheev", "Oleg", 3, Math.ceil(Math.random() * 100000))),
            new Employee("Miheev", "Oleg", 3, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Eliseev", "Kassian", 4, Math.ceil(Math.random() * 100000))),
            new Employee("Eliseev", "Kassian", 4, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Ustinov", "Modest", 5, Math.ceil(Math.random() * 100000))),
            new Employee("Ustinov", "Modest", 5, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Efimov", "Vol'demar", 1, Math.ceil(Math.random() * 100000))),
            new Employee("Efimov", "Vol'demar", 1, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Sharapov", "Ermak", 2, Math.ceil(Math.random() * 100000))),
            new Employee("Sharapov", "Ermak", 2, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Orlov", "Ivan", 3, Math.ceil(Math.random() * 100000))),
            new Employee("Orlov", "Ivan", 3, Math.ceil(Math.random() * 100000)),
            getKey(new Employee("Kovalyov", "Dem'yan", 4, Math.ceil(Math.random() * 100000))),
            new Employee("Kovalyov", "Dem'yan", 4, Math.ceil(Math.random() * 100000))
    ));

    public Map<String, Employee> getMap() {
        return employees;
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        String key = firstName + lastName;
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Запись отсутствует");
        }
        return employees.get(key);
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int departmentId, double salary) {
        String key = firstName + lastName;
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Запись уже существует");
        }
        Employee employee = new Employee(firstName, lastName, departmentId, salary);
        employees.put(key, employee);
        return employee;
    }

    private String getKey(Employee employee) {
        return employee.getLastName() + " " + employee.getFirstName();
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = firstName + lastName;
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Запись отсутствует");
        }
        Employee employee = employees.get(key);
        employees.remove(key);
        return employee;
    }

    @Override
    public Employee changeSalary(Employee employee, double newSalary) {
        String key = getKey(employee);
        if (employees.containsKey(key)) {
            employees.get(key).setSalary(newSalary);
        }
        return employee;
    }

    @Override
    public Employee changeDepartment(Employee employee, int newDepartment) {
        String key = getKey(employee);
        if (employees.containsKey(key)) {
            employees.get(key).setDepartment(newDepartment);
        }
        return employee;
    }

    @Override
    public double getSum() {
        var lambdaContext = new Object() {
            double sum = 0;
        };
        List<Employee> e = new ArrayList<>(employees.values());
        e.forEach(employee -> lambdaContext.sum = lambdaContext.sum + employee.getSalary());
        return lambdaContext.sum;
    }

    @Override
    public double averageSalary() {
        List<Employee> e = new ArrayList<>(employees.values());
        return getSum() / e.size();
    }


}

