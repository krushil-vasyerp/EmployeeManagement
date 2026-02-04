package project.emp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emp.ExceptionHandler.ResourceNotFoundException;
import project.emp.Model.Departments;
import project.emp.Repository.DepartmentsRepository;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentsRepository drepo;

    public List<Departments> getAllDepartments() {
        return drepo.findAll();
    }

    public Departments getDepartmentById(Integer id) {
        return drepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    public Departments addDepartment(Departments department) {
        return drepo.save(department);
    }

    public Departments updateDepartment(Integer id, Departments department) {
        Departments existing = getDepartmentById(id);
        department.setId(existing.getId());
        return drepo.save(department);
    }

    public void deleteDepartment(Integer id) {
        Departments department = getDepartmentById(id);
        drepo.delete(department);
    }

    public List<?> findDepartmentsWithEmployees(){
        return drepo.findDepartmentsWithEmployees();
    }
}
