package pro.sky.homeworks.homework213.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homeworks.homework213.Employee;
import pro.sky.homeworks.homework213.exceptions.DeptNotFoundException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static pro.sky.homeworks.homework213.Constants.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    private final Employee employee = new Employee(FIRSTNAME, LASTNAME, ONE, TWO);
    @Mock
    private EmployeeServiceImpl employeeServiceMock;
    @Mock
    private DepartmentService out;


    @Test
    void shouldGetEmployeeWithMaxSalaryInDept() {
        when(out.haveMaxSalaryInDept(ONE)).thenReturn(new Employee(FIRSTNAME, LASTNAME, ONE, TWO));

        assertEquals(out.haveMaxSalaryInDept(ONE), employee);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetEmployeeWithMaxSalaryInDept() {
        when(out.haveMaxSalaryInDept(TWO)).thenThrow(DeptNotFoundException.class);
        Throwable thrown = catchThrowable(() -> out.haveMaxSalaryInDept(TWO));
        assertThat(thrown).isInstanceOf(DeptNotFoundException.class);
    }

    @Test
    void shouldGetEmployeeWithMinSalaryInDept() {
        when(out.haveMaxSalaryInDept(ONE)).thenReturn(new Employee(FIRSTNAME, LASTNAME, ONE, TWO));
        assertEquals(out.haveMaxSalaryInDept(ONE), employee);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetEmployeeWithMinSalaryInDept() {
        when(out.haveMinSalaryInDept(TWO)).thenThrow(DeptNotFoundException.class);
        Throwable thrown = catchThrowable(() -> out.haveMinSalaryInDept(TWO));
        assertThat(thrown).isInstanceOf(DeptNotFoundException.class);
    }


    @Test
    void shouldShowAllByDept() {
        List<Employee> result = new ArrayList<>(List.of(employee));
        when(out.getAllByDept(ONE)).thenReturn(result);
        assertEquals(employee, out.getAllByDept(ONE).get(ZERO));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetAllEmployeesInDept() {
        when(out.getAllByDept(TWO)).thenThrow(DeptNotFoundException.class);
        Throwable thrown = catchThrowable(() -> out.getAllByDept(TWO));
        assertThat(thrown).isInstanceOf(DeptNotFoundException.class);
    }

    @Test
    void shouldShowAll() {
        Map<Integer, List<Employee>> result = new HashMap<>(Map.of(ONE, new ArrayList<>(List.of(employee))));
        when(out.getAll()).thenReturn(result);
        assertEquals(employee, out.getAll().get(ONE).get(ZERO));
    }
}