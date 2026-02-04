package project.emp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emp.ExceptionHandler.ResourceNotFoundException;
import project.emp.Model.Employees;
import project.emp.Repository.EmployeesRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeesRepository erepo;

    public List<Employees> getallEmployees() {
        return erepo.findAll();
    }

    public Employees findEmployeeById(Integer id) {
        return erepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(   "Employee not found with id: " + id));
    }

    public Employees addEmployee(Employees e) {
        return erepo.save(e);
    }

    public Employees updateEmployee(Employees e , Integer id) {
        Employees existing = findEmployeeById(id);
        e.setId(existing.getId());
        return erepo.save(e);
    }

    public void deleteEmployee(Integer id) {
        Employees existing = findEmployeeById(id);
        erepo.delete(existing);
    }
}
