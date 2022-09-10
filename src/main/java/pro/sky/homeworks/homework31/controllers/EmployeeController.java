package pro.sky.homeworks.homework31.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homeworks.homework31.Employee;
import pro.sky.homeworks.homework31.exceptions.BadRequestException;
import pro.sky.homeworks.homework31.services.DepartmentService;
import pro.sky.homeworks.homework31.services.EmployeeService;


import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    private EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("departmentId") int departmentId,
                                @RequestParam("salary") double salary) throws BadRequestException {
        if (!Objects.equals(firstName, StringUtils.capitalize(firstName)) || !Objects.equals(lastName, StringUtils.capitalize(lastName))){
         throw new BadRequestException("Неверный формат запроса");
        }
        return employeeService.addEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) throws BadRequestException {
        if (!Objects.equals(firstName, StringUtils.capitalize(firstName)) || !Objects.equals(lastName, StringUtils.capitalize(lastName))){
            throw new BadRequestException("Неверный формат запроса");
        }
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) throws BadRequestException {
        if (!Objects.equals(firstName, StringUtils.capitalize(firstName)) || !Objects.equals(lastName, StringUtils.capitalize(lastName))){
            throw new BadRequestException("Неверный формат запроса");
        }
        return employeeService.getEmployee(firstName,lastName);
    }

    @GetMapping()
    public Map<Integer, List<Employee>> getEmployees() {
        return departmentService.getAll();
    }
}