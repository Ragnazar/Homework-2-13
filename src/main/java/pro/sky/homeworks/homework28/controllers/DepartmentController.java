package pro.sky.homeworks.homework28.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homeworks.homework28.Employee;
import pro.sky.homeworks.homework28.services.DepartmentService;

import java.util.Collection;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping(path = "/max-salary")
    public Employee addEmployee(@RequestParam("departmentId") int departmnetId) {
        return departmentService.haveMaxSalaryInDept(departmnetId);
    }

    @GetMapping(path = "/min-salary")
    public Employee removeEmployee(@RequestParam("departmentId") int departmnetId) {
        return departmentService.haveMinSalaryInDept(departmnetId);
    }

    @GetMapping(path = "/all")
    public Collection<Employee> findEmployee(@RequestParam("departmentId") int departmnetId) {
        return departmentService.getAllByDept(departmnetId);
    }

    @GetMapping(path = "/al")
    public Collection<Employee> getEmployees() {
        return departmentService.getAll();
    }
}
