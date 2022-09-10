package pro.sky.homeworks.homework31.controllers;

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

import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.springframework.util.StringUtils.capitalize;

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
        validateInput(firstName,lastName);
        return employeeService.addEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) throws BadRequestException {
        validateInput(firstName,lastName);
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) throws BadRequestException {
        validateInput(firstName,lastName);
        return employeeService.getEmployee(firstName,lastName);
    }

    @GetMapping()
    public Map<Integer, List<Employee>> getEmployees() {
        return departmentService.getAll();
    }

    private void validateInput(String firstName, String lastName) throws BadRequestException {
        if (!(isAlpha(firstName) && isAlpha(lastName))
                || (!firstName.equals(capitalize(firstName)) && !lastName.equals(capitalize(lastName)))){
            throw new BadRequestException("Неверный формат запроса");
        }
    }

}