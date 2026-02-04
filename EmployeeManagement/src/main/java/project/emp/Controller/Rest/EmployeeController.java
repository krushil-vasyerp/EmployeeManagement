package project.emp.Controller.Rest;


import org.springframework.web.bind.annotation.*;
import project.emp.Model.Employees;
import project.emp.Service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    private final EmployeeService eservice;
    //constructor
    public EmployeeController(EmployeeService eservice) {
        this.eservice = eservice;
    }
    //normal employee crud
    @GetMapping
    public List<Employees> allEmployees(){
        return eservice.getallEmployees();
    }

    @GetMapping("/{id}")
    public Employees findEmployee(@PathVariable  Integer id){
        return eservice.findEmployeeById(id);
    }

    @PostMapping
    public Employees addEmployee(@RequestBody Employees e){
        eservice.addEmployee(e);
        return e;
    }

    @PutMapping("/{id}")
    public Employees updateEmployee(@RequestBody Employees e , @PathVariable Integer id){
        eservice.updateEmployee(e , id);
        return e;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        eservice.deleteEmployee(id);
    }

}
