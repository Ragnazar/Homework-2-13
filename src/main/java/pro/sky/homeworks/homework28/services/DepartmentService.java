package pro.sky.homeworks.homework28.services;
import pro.sky.homeworks.homework28.Employee;

import java.util.Collection;

public interface DepartmentService {

    Employee addEmployee(Employee employee);

    Employee removeEmployee(Employee employee);


    Employee changeSalary(Employee employee, double newSalary);

    Employee changeDepartment(Employee employee, int newDepartment);

    double getSum();


    double averageSalary();

    Employee haveMaxSalaryInDept(int departmnetId);

    Employee haveMinSalaryInDept(int departmnetId);

    Collection<Employee> getAllByDept(int departmnetId);

    Collection<Employee> getAll();
}
