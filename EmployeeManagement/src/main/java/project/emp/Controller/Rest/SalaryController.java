package project.emp.Controller.Rest;


import org.springframework.web.bind.annotation.*;
import project.emp.Model.Salary;
import project.emp.Service.SalaryService;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    private SalaryService sservice;
    //constructor
    public SalaryController(SalaryService sservice) {
        this.sservice = sservice;
    }
    //normal salary related read
    @GetMapping
    public List<Salary> getSalary(){
        return sservice.getAllSalaries();
    }

    //for other update delete or put, need employee which accepting salary
    @GetMapping("/employee/{employeeId}")
    public Salary getSalaryById(@PathVariable Integer employeeId){
        return sservice.getSalaryByEmpId(employeeId);
    }

    @PostMapping("/employee/{employeeId}")
    public Salary addSalary(@PathVariable Integer employeeId , @RequestBody Salary salary){
        return sservice.addSalary(employeeId,salary);
    }

    @PutMapping("/employee/{employeeId}")
    public Salary updateSalary(@PathVariable Integer employeeId ,@RequestBody Salary salary){
        return sservice.updateSalary(employeeId,salary);
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteSalary(@PathVariable Integer employeeId){
        sservice.deleteSalary(employeeId);
    }

    @GetMapping("/highest")
    public Salary highestSalary(){
        return sservice.findHighestSalary();
    }
}
