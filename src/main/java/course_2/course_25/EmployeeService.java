package course_2.course_25;

public interface EmployeeService {
    String addEml(String firstName, String lastName);

    String removeEml(String firstName, String lastName);
//    Boolean findEml(Employee employee);

    String findEml(String firstName, String lastName);
}
