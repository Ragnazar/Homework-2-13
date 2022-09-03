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
    public Optional<Employee> haveMaxSalaryInDept(int departmentId) {
        List<Employee> filter = getAllByDept(departmentId);
        filter.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        return filter.stream().findFirst();
    }


    @Override
    public Optional<Employee> haveMinSalaryInDept(int departmentId) {
        List<Employee> filter = getAllByDept(departmentId);
        filter.sort(Comparator.comparingDouble(Employee::getSalary));
        return filter.stream().findFirst();
    }

    @Override
    public List<Employee> getAllByDept(int departmentId) {
        List<Employee> result = new ArrayList<>();
        employees.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDepartment() == departmentId)
                .forEach(entry -> result.add(entry.getValue()));
        return result;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>(employees.values());
        result.sort(Comparator.comparingInt(Employee::getDepartment));
        return result;
    }
}

