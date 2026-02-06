package project.emp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emp.ExceptionHandler.ResourceNotFoundException;
import project.emp.Model.Departments;
import project.emp.Model.Employees;
import project.emp.Model.Enums.DepartmentTypes;
import project.emp.Repository.DepartmentsRepository;
import project.emp.Repository.EmployeesRepository;

import java.util.List;
import java.util.Optional;

import static project.emp.Model.Enums.DepartmentTypes.ADMINISTRATION;

@Service
public class EmployeeService {

    private DepartmentsRepository drepo;
    private EmployeesRepository erepo;

    public EmployeeService(DepartmentsRepository drepo, EmployeesRepository erepo) {
        this.drepo = drepo;
        this.erepo = erepo;
    }

    public List<Employees> getallEmployees() {
        return erepo.findAll();
    }

    public Employees findEmployeeById(Integer id) {
        return erepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(   "Employee not found with id: " + id));
    }

    public Employees addEmployee(Employees e) {

        // Fetch real department from DB
        Departments realDept = drepo.findById(
                e.getDepartment().getId()
        ).orElseThrow(() ->
                new RuntimeException("Department not found"));

        if (realDept.getName() == DepartmentTypes.ADMINISTRATION) {

            Optional<Employees> existing =
                    erepo.findFirstByDepartment(realDept);

            if (existing.isPresent()) {
                throw new RuntimeException(
                        "Only one employee allowed in ADMINISTRATION department");
            }
        }

        e.setDepartment(realDept);
        return erepo.save(e);
    }

    public Employees updateEmployee(Employees e, Integer id) {

        Employees existingEmp = findEmployeeById(id);

        Departments realDept = drepo.findById(
                e.getDepartment().getId()
        ).orElseThrow(() ->
                new RuntimeException("Department not found"));

        if (realDept.getName() == DepartmentTypes.ADMINISTRATION) {

            Optional<Employees> existingAdmin =
                    erepo.findFirstByDepartment(realDept);

            if (existingAdmin.isPresent() &&
                    !existingAdmin.get().getId().equals(existingEmp.getId())) {

                throw new RuntimeException(
                        "Only one employee allowed in ADMINISTRATION department");
            }
        }

        e.setId(existingEmp.getId());
        e.setDepartment(realDept);

        return erepo.save(e);
    }



    public void deleteEmployee(Integer id) {
        Employees existing = findEmployeeById(id);
        erepo.delete(existing);
    }
}
