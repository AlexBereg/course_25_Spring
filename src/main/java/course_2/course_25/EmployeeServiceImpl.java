package course_2.course_25;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    static List<Employee> emloyeeList = new ArrayList<>();
    static int maxEml = 2;

    public static void checkingList(String firstName, String lastName) {
        if (emloyeeList.contains(new Employee(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded (Уже есть сотрудник!!)");
        }
    }

    public static void checkingListNot(String firstName, String lastName) {
        if (!emloyeeList.contains(new Employee(firstName, lastName))) {
            throw new EmployeeNotFoundException("EmployeeNotFound (Нет сотрудника!)");
        }
    }

    public static void checkingListLength() {
        if (emloyeeList.size() == maxEml) {
            throw new EmployeeStorageIsFullException("ArrayIsFull (Переполнен)");
        }
    }

    @Override
    public String addEml(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            checkingList(firstName, lastName);
            try {
                checkingListLength();
                emloyeeList.add(employee);
                return employee.toString();
            } catch (EmployeeStorageIsFullException e) {
                return e.getMessage();
            }
        } catch (EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
    }

    @Override
    public String findEml(String firstName, String lastName) {
        try {
            checkingListNot(firstName, lastName);
            return (new Employee(firstName, lastName)).toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String allPrint() {
        return emloyeeList.toString();
    }

    @Override
    public String removeEml(String firstName, String lastName) {
        try {
            checkingListNot(firstName, lastName);
            Employee employee = new Employee(firstName, lastName);
            for (int i = 0; i < emloyeeList.size(); i++) {
                if (employee.equals(emloyeeList.get(i))) {
                    emloyeeList.remove(i);
                    break;
                }
            }
            return employee.toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }
}
