package pro.sky.homeworks.homework31.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homeworks.homework31.Employee;
import pro.sky.homeworks.homework31.services.DepartmentService;


import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee haveMaxSalaryInDept(@RequestParam("departmentId") int departmentId) {
        return departmentService.haveMaxSalaryInDept(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee haveMinSalaryInDept(@RequestParam("departmentId") int departmentId) {
        return departmentService.haveMinSalaryInDept(departmentId);
    }

    @GetMapping(path = "/al")
    public Map<Integer, List<Employee>> getEmployees() {
        return departmentService.getAll();
    }

    @GetMapping(path = "/all")
    public Collection<Employee> findEmployee(@RequestParam(value = "departmentId", required = false) int departmentId) {
        return departmentService.getAllByDept(departmentId);
    }
}
