package course_2.course_25.service;

import course_2.course_25.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMaxSalary(int depart) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    public Employee findMinSalary(int depart) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    public Collection<Employee> findByDepart(int depart) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .toList();
    }

    public Map<Integer, List<Employee>> groupDepart() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
