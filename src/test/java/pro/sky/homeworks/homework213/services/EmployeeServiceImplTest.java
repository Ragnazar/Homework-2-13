package pro.sky.homeworks.homework213.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.homeworks.homework213.Employee;
import pro.sky.homeworks.homework213.exceptions.EmployeeAlreadyAddedException;
import pro.sky.homeworks.homework213.exceptions.EmployeeNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.homeworks.homework213.Constants.*;

@SpringBootTest
class EmployeeServiceImplTest {
    EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployee() {
        Employee actual = employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, TWO);
        Employee expected = new Employee(FIRSTNAME, LASTNAME, ONE, TWO);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedException() {
        employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, TWO);
        Throwable thrown = catchThrowable(() -> employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, TWO));
        assertThat(thrown).isInstanceOf(EmployeeAlreadyAddedException.class);
        assertThat(thrown.getMessage()).isNotBlank();
    }

    @Test
    void shouldAddEmployeeButLastNameAndFirstNameExpected() {
        Throwable thrown = catchThrowable(() -> employeeService.addEmployee(null, null, ONE, TWO));
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }


    @Test
    void intInSalaryParam() {
        employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, ONE);
    }

    @Test
    void shouldGetEmployee() {
        Employee expected = employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, TWO);
        Employee actual = employeeService.getEmployee(FIRSTNAME, LASTNAME);
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetEmployeeButGotNotFoundException() {
        Throwable thrown = catchThrowable(() -> employeeService.getEmployee(FIRSTNAME, LASTNAME));
        assertThat(thrown).isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    void shouldRemoveEmployee() {
        employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, TWO);
        employeeService.removeEmployee(FIRSTNAME, LASTNAME);
        Throwable thrown = catchThrowable(() -> employeeService.getEmployee(FIRSTNAME, LASTNAME));
        assertThat(thrown).isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    void shouldRemoveEmployeeButGotNotFoundException() {
        Throwable thrown = catchThrowable(() -> employeeService.removeEmployee(FIRSTNAME, LASTNAME));
        assertThat(thrown).isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    void shouldChangeSalary() {
        Employee employee = employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, TWO);
        Employee actual = employeeService.changeSalary(employee, ONE);
        assertEquals(ONE, actual.getSalary());
    }

    @Test
    void shouldChangeDepartment() {
        Employee employee = employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, TWO);
        Employee actual = employeeService.changeDepartment(employee, ZERO);
        assertEquals(ZERO, actual.getDepartment());
    }

    @Test
    void shouldGetSumCorrectly() {
        double expected = employeeService.getSum();
        double actual = employeeService.getSum();
        assertEquals(expected, actual - TWO);
    }

    @Test
    void shouldChangeAverageSalaryAfterAddingNewEmployee() {
        double expected = employeeService.averageSalary();
        employeeService.addEmployee(FIRSTNAME, LASTNAME, ONE, TWO);
        double actual = employeeService.averageSalary();
        assertNotEquals(expected, actual);
    }
}