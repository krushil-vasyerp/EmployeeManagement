package project.emp.Service;

import org.springframework.stereotype.Service;
import project.emp.ExceptionHandler.ResourceNotFoundException;
import project.emp.Model.Employees;
import project.emp.Model.Tasks;
import project.emp.Repository.EmployeesRepository;
import project.emp.Repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository trepo;
    private EmployeesRepository emprepo;

    public TaskService(EmployeesRepository emprepo, TaskRepository trepo) {
        this.emprepo = emprepo;
        this.trepo = trepo;
    }

    public Tasks getTaskById(Integer taskId) {
        return trepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
    }

    public List<Tasks> getTask() {
        return trepo.findAll();
    }

    public void deleteTask(Integer taskId) {
        Tasks task = getTaskById(taskId);
        trepo.delete(task);
    }

    public Tasks updateTask(Integer taskId, Tasks task) {
        Tasks existing = trepo.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        task.setId(existing.getId());
        task.setEmployee(existing.getEmployee());//prevent change of task ownership
        return trepo.save(task);
    }

    public Tasks addTask(Integer employeeId, Tasks task) {
        Employees e = emprepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        task.setEmployee(e);
        return trepo.save(task);
    }

    public List<Tasks> getTasksByEmployee(Integer employeeId) {
        Employees employee = emprepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        return trepo.findByEmployee(employee);
    }

    public List<Tasks> findByStatus(String status){
        return trepo.findByStatus(status);
    };
}
