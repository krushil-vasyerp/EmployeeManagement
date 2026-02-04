package project.emp.Controller;


import org.springframework.web.bind.annotation.*;
import project.emp.Model.Departments;
import project.emp.Service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService dservice;
    //constructor
    public DepartmentController(DepartmentService dservice) {
        this.dservice = dservice;
    }

    //normal crud operation of department details
    @GetMapping
    public List<Departments> getAllDepartments() {
        return dservice.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Departments getDepartmentById(@PathVariable Integer id) {
        return dservice.getDepartmentById(id);
    }

    @PostMapping
    public Departments addDepartment(@RequestBody Departments department) {
        return dservice.addDepartment(department);
    }

    // can perform if you have already defined data in enums
    // also to add data you have to add first department in enum that type of department only you can create

    @PutMapping("/{id}")
    public Departments updateDepartment(
            @PathVariable Integer id,
            @RequestBody Departments department) {
        return dservice.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        dservice.deleteDepartment(id);
    }

    @GetMapping("/employees")
    public List<?> getDepartmentByEmployee() {
        return dservice.findDepartmentsWithEmployees();
    }
}
