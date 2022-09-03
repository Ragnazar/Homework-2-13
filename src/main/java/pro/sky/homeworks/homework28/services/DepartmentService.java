package pro.sky.homeworks.homework28.services;

import pro.sky.homeworks.homework28.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Employee addEmployee(Employee employee);

    Employee removeEmployee(Employee employee);


    Employee changeSalary(Employee employee, double newSalary);

    Employee changeDepartment(Employee employee, int newDepartment);

    double getSum();


    double averageSalary();

    Optional<Employee> haveMaxSalaryInDept(int departmentId);

    Optional<Employee> haveMinSalaryInDept(int departmentId);

    List<Employee> getAllByDept(int departmentId);

    List<Employee> getAll();
}
