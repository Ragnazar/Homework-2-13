package pro.sky.homeworks.homework31.services;

import pro.sky.homeworks.homework31.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee removeEmployee(String firstName, String lastname);


    Employee changeSalary(Employee employee, double newSalary);

    Employee changeDepartment(Employee employee, int newDepartment);

    double getSum();


    double averageSalary();

    Map<String, Employee> getMap();

    Employee getEmployee(String firstName, String lastName);

    Employee addEmployee(String firstName, String lastName, int departmentId, double salary);
}
