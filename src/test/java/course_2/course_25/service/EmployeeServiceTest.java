package course_2.course_25.service;

import course_2.course_25.excepotion.EmployeeAlreadyAddedException;
import course_2.course_25.excepotion.EmployeeNotFoundException;
import course_2.course_25.excepotion.EmployeeStorageIsFullException;
import course_2.course_25.excepotion.NameException;
import course_2.course_25.model.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    void addEmlTest() {
        employeeService.addEml("ivan", "ivanov", 1, 10000);
        employeeService.addEml("Ivana", "Ivanova", 2, 20000);

        var actual1 = employeeService.findEml("Ivan", "Ivanov");
        assertThat(actual1).isNotNull();
        assertThat(actual1.toString().equals("Ivan Ivanov"));

        var actual2 = employeeService.findEml("Ivana", "Ivanov");
        assertThat(actual2).isNotNull();
        assertThat(actual2.toString().equals("Ivana Ivanova"));
    }

    @Test
    void addEmlDublicateTest() {
        employeeService.addEml("ivan", "ivanov", 1, 10000);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEml("ivan", "ivanov", 2, 20000));
    }

    @Test
    void TestFull() {
        employeeService.addEml("a", "aa", 1, 10000);
        employeeService.addEml("aa", "aa", 2, 10000);
        employeeService.addEml("aaa", "aa", 1, 10000);
        employeeService.addEml("aaaa", "aa", 1, 10000);
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEml("ivan", "ivanov", 2, 200));
    }

    @Test
    void UncorrectedName() {
        assertThrows(NameException.class, () -> employeeService.addEml("ivan1", "1ivanov", 2, 200));

    }

    @Test
    void removeEmlTest() {
        employeeService.addEml("Ivan", "Ivanov", 1, 10000);
        employeeService.removeEml("Ivan", "Ivanov");
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEml("Ivan", "Ivanov"));
    }

    @Test
    void getAllTest() {
        employeeService.addEml("a", "aa", 1, 10000);
        employeeService.addEml("aa", "aa", 2, 10000);
        var actual = employeeService.getAll();
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("A", "Aa", 1, 10000),
                new Employee("Aa", "Aa", 2, 10000)
        );
    }
}