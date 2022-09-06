package pro.sky.homeworks.homework28.services;

import pro.sky.homeworks.homework28.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee removeEmployee(Employee employee);


    Employee changeSalary(Employee employee, double newSalary);

    Employee changeDepartment(Employee employee, int newDepartment);

    double getSum();


    double averageSalary();

    Map<String, Employee> getMap();

}
