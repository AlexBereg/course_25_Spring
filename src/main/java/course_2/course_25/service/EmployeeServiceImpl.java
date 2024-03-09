package course_2.course_25.service;

import course_2.course_25.excepotion.NameException;
import course_2.course_25.model.Employee;
import course_2.course_25.excepotion.EmployeeAlreadyAddedException;
import course_2.course_25.excepotion.EmployeeNotFoundException;
import course_2.course_25.excepotion.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employeeMap = new HashMap<>();
    private int maxEml = 5;

    private void checkingMap(String key) {
        if (employeeMap.get(key) != null) {
            throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded (Уже есть сотрудник!!)");
        }
    }

    private void checkingMapLength() {
        if (employeeMap.size() >= maxEml) {
            throw new EmployeeStorageIsFullException("ArrayIsFull (Переполнен)");
        }
    }

    private  void checkingMapNot(String key) {
        if (employeeMap.get(key) == null) {
            throw new EmployeeNotFoundException("EmployeeNotFound (Нет сотрудника!)");
        }
    }

    @Override
    public String addEml(String firstName, String lastName, int department, int salary) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new NameException("Некорректно введены данные!");
        }

        String key = firstName.toUpperCase() + "*" + lastName.toUpperCase();
//        try {
            checkingMapLength();
//            try {
                checkingMap(key);
                Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), department, salary);
                employeeMap.put(key, employee);
                return employee.toString();
//            } catch (EmployeeAlreadyAddedException e) {
//                return e.getMessage();
//            }
//        } catch (EmployeeStorageIsFullException e) {
//            return e.getMessage();
//        }
    }

    @Override
    public String removeEml(String firstName, String lastName) {
        String key = firstName.toUpperCase() + "*" + lastName.toUpperCase();
//        try {
            checkingMapNot(key);
            Employee employee2 = employeeMap.get(key);
            employeeMap.remove(key);
            return employee2.toString();
//        } catch (EmployeeNotFoundException e) {
//            return e.getMessage();
//        }
    }

    @Override
    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }

    @Override
    public String findEml(String firstName, String lastName) {
        String key = firstName.toUpperCase() + "*" + lastName.toUpperCase();
//        try {
            checkingMapNot(key);
            return employeeMap.get(key).toString();
//        } catch (EmployeeNotFoundException e) {
//            return e.getMessage();
//        }
    }

    @Override
    public String allPrint() {
        return employeeMap.values().toString();
    }
}
