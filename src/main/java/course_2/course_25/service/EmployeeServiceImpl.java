package course_2.course_25.service;

import course_2.course_25.model.Employee;
import course_2.course_25.excepotion.EmployeeAlreadyAddedException;
import course_2.course_25.excepotion.EmployeeNotFoundException;
import course_2.course_25.excepotion.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//    public EmployeeServiceImpl() {

    static Map<String, Employee> employeeMap = new HashMap<>();
    static int maxEml = 2;

    public static void checkingMap(String key) {
        if (employeeMap.get(key) != null) {
            throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded (Уже есть сотрудник!!)");
        }
    }

    public static void checkingMapLength() {
        if (employeeMap.size() >= maxEml) {
            throw new EmployeeStorageIsFullException("ArrayIsFull (Переполнен)");
        }
    }

    public static void checkingMapNot(String key) {
        if (employeeMap.get(key) == null) {
            throw new EmployeeNotFoundException("EmployeeNotFound (Нет сотрудника!)");
        }
    }

    @Override
    public String addEml(String firstName, String lastName) {
        String key = firstName.toUpperCase() + "*" + lastName.toUpperCase();
        try {
            checkingMapLength();
            try {
                checkingMap(key);
                Employee employee = new Employee(firstName, lastName);
                employeeMap.put(key, employee);
                return employee.toString();
            } catch (EmployeeAlreadyAddedException e) {
                return e.getMessage();
            }
        } catch (EmployeeStorageIsFullException e) {
            return e.getMessage();
        }
    }

    @Override
    public String removeEml(String firstName, String lastName) {
        String key = firstName.toUpperCase() + "*" + lastName.toUpperCase();
        try {
            checkingMapNot(key);
            Employee employee2 = employeeMap.get(key);
            employeeMap.remove(key);
            return employee2.toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String findEml(String firstName, String lastName) {
        String key = firstName.toUpperCase() + "*" + lastName.toUpperCase();
        try {
            checkingMapNot(key);
            return employeeMap.get(key).toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String allPrint() {
        return employeeMap.values().toString();
    }
}
