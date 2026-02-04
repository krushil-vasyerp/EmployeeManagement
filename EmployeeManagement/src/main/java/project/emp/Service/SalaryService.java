package project.emp.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emp.ExceptionHandler.ResourceNotFoundException;
import project.emp.Model.Employees;
import project.emp.Model.Salary;
import project.emp.Repository.EmployeesRepository;
import project.emp.Repository.SalaryRepository;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository srepo;
    @Autowired
    private EmployeesRepository emprepo;


    public List<Salary> getAllSalaries() {
        return srepo.findAll();
    }

    public Salary getSalaryByEmpId(Integer employeeId) {
        Employees e = emprepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        return srepo.findByEmployee(e).orElse(null);
    }

    public Salary addSalary(Integer employeeId ,Salary salary) {
        Employees emp = emprepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if(srepo.existsByEmployee(emp)){
            throw new ResourceNotFoundException("Salary for Employee already exists");
        }

        salary.setEmployee(emp);
        salary.setNetSalary(
                salary.getBasicSalary()
                        + (salary.getBonus() != null ? salary.getBonus() : 0)
                        - (salary.getDeduction() != null ? salary.getDeduction() : 0)
        );
        return srepo.save(salary);
    }

    public Salary updateSalary(Integer employeeId ,Salary salary) {
        Employees employee = emprepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        Salary existing = srepo.findByEmployee(employee)
                .orElseThrow(() -> new ResourceNotFoundException("Salary not found for this employee"));

        salary.setId(existing.getId());
        salary.setEmployee(employee);

        salary.setNetSalary(
                salary.getBasicSalary()
                        + (salary.getBonus() != null ? salary.getBonus() : 0)
                        - (salary.getDeduction() != null ? salary.getDeduction() : 0)
        );
        return srepo.save(salary);
    }
    @Transactional
    public void deleteSalary(Integer employeeId) {
        Employees employee = emprepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setSalary(null);
    }


    public Salary findHighestSalary(){
        return srepo.findHighestSalary();
    }
}
