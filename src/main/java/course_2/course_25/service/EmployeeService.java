package course_2.course_25.service;

import course_2.course_25.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    String addEml(String firstName, String lastName, int department, int salary);
    String allPrint();

    String findEml(String firstName, String lastName);
    String removeEml(String firstName, String lastName);

    public Collection<Employee> getAll();

}
