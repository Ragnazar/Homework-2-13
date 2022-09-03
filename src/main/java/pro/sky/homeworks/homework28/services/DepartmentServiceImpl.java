package pro.sky.homeworks.homework28.services;

import org.springframework.stereotype.Service;
import pro.sky.homeworks.homework27.exceptions.EmployeeNotFoundException;
import pro.sky.homeworks.homework28.Employee;

import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    Map<String, Employee> employees = new HashMap<>(Map.of(
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

    private String getKey(Employee employee) {
        return employee.getLastName() + " " + employee.getFirstName();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(Employee employee) {
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException("Запись отсутствует");
        }
        employees.remove(getKey(employee));
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

    @Override
    public Employee haveMaxSalaryInDept(int departmentId) {
        double maxSalary = Double.MIN_VALUE;
        String key = null;
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            if (entry.getValue().getDepartment() == departmentId && maxSalary < entry.getValue().getSalary()) {
                maxSalary = entry.getValue().getSalary();
                key = entry.getKey();
            }
        }
        return employees.get(key);
    }

    @Override
    public Employee haveMinSalaryInDept(int departmentId) {
        double minSalary = Double.MAX_VALUE;
        String key = null;
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            if (entry.getValue().getDepartment() == departmentId && minSalary > entry.getValue().getSalary()) {
                minSalary = entry.getValue().getSalary();
                key = entry.getKey();
            }
        }
        return employees.get(key);
    }

    @Override
    public Collection<Employee> getAllByDept(int departmentId) {
        List<Employee> result = new ArrayList<>();
        String key = null;
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            if (entry.getValue().getDepartment() == departmentId) {
                key = entry.getKey();
            }
            result.add(employees.get((key)));
        }
        return result;
    }

    @Override
    public Collection<Employee> getAll() {
        List<Employee> result = new ArrayList<>(employees.values());
        result.sort(Comparator.comparingInt(Employee::getDepartment));
        return result;
    }
}

