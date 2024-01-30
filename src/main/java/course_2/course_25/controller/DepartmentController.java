package course_2.course_25.controller;

import course_2.course_25.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public String maxSalaryDep(@RequestParam(value = "departmentId") int departmentId) {
        return departmentService.findMaxSalary(departmentId).toString();
    }

    @GetMapping(path = "/min-salary")
    public String minSalaryDep(@RequestParam(value = "departmentId") int departmentId) {
        return departmentService.findMinSalary(departmentId).toString();
    }

    @GetMapping("/all" )
    public String allByDep() {
        return departmentService.groupDepart().toString();
    }
    @GetMapping(value = "/all", params ={"departmentId"})
    public String allByDep(@RequestParam(value = "departmentId") int departmentId) {
        return departmentService.findByDepart(departmentId).toString();
    }
}
