package course_2.course_25.controller;

import course_2.course_25.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        return employeeService.addEml(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public String find(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        return employeeService.findEml(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public String remove(@RequestParam(value = "firstName") String
                                 firstName, @RequestParam(value = "lastName") String lastName) {
        return employeeService.removeEml(firstName, lastName);
    }

    @GetMapping(path = "/all")
    public String allPrint() {
        return employeeService.allPrint();
    }
}
