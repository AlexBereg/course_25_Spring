package course_2.course_25.service;

import course_2.course_25.model.Employee;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        var employees = List.of(
                new Employee("test", "test", 1, 10000),
                new Employee("test2", "test", 1, 100000),
                new Employee("test3", "test", 2, 20000),
                new Employee("test4", "test4", 2, 200000),
                new Employee("test5", "test", 3, 30000)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void findMaxSalaryTest() {
        assertThat(departmentService.findMaxSalary(1)).isEqualTo(new Employee("test2", "test", 1, 100000));
    }

    @Test
    void findMinSalaryTest() {
        var actual = departmentService.findMinSalary(1);
        assertThat(actual.getFirstName()).isEqualTo("test");
    }

    @Test
    void findByDepartTest() {
        assertThat(departmentService.findByDepart(2)).containsExactlyInAnyOrder(
                new Employee("test3", "test", 2, 20000),
                new Employee("test4", "test4", 2, 200000)
        );
    }

    @Test
    void groupDepartTest() {
        var actual = departmentService.groupDepart();
        var expected = Map.of(
                1,List.of(
                        new Employee("test", "test", 1, 10000),
                        new Employee("test2", "test", 1, 100000)),
                2,List.of(
                        new Employee("test3", "test", 2, 20000),
                        new Employee("test4", "test4", 2, 200000)),
                3,List.of(
                        new Employee("test5", "test", 3, 30000))
        );
        assertThat(actual).isEqualTo(expected);
    }
}