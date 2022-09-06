package pro.sky.homeworks.homework31.services;

import pro.sky.homeworks.homework31.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee haveMaxSalaryInDept(int departmentId);

    Employee haveMinSalaryInDept(int departmentId);

    List<Employee> getAllByDept(int departmentId);

    Map<Integer, List<Employee>> getAll();
}
