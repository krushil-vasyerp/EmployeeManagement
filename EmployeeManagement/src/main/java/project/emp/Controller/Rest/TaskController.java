package project.emp.Controller.Rest;


import org.springframework.web.bind.annotation.*;
import project.emp.Model.Tasks;
import project.emp.Service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService tservice;
    //constructor
    public TaskController(TaskService tservice) {
        this.tservice = tservice;
    }
    //normal task details crud
    @GetMapping("/{taskId}")
    public Tasks getTasksById(@PathVariable Integer taskId){
        return tservice.getTaskById(taskId);
    }

    @GetMapping
    public List<Tasks> getTask(){
        return tservice.getTask();
    }

    @PutMapping("/{taskId}")
    public Tasks updateTask(@PathVariable Integer taskId, @RequestBody Tasks task){
        return tservice.updateTask(taskId , task);
    }

    @DeleteMapping("/{taskId}")
    public void  deleteTask(@PathVariable Integer taskId){
        tservice.deleteTask(taskId);
    }

    //all is related with employee becoz employee has only task assign
    @PostMapping("/employee/{employeeId}")
    public Tasks addTask(@PathVariable Integer employeeId, @RequestBody Tasks task){
        return tservice.addTask(employeeId,task);
    }
    @GetMapping("/employee/{employeeId}")
    public List<Tasks> getTasksByEmployee(@PathVariable Integer employeeId){
        return tservice.getTasksByEmployee(employeeId);
    }

    @GetMapping("/status/{status}")
    public List<Tasks> findByStatus(@PathVariable String status){
        return tservice.findByStatus(status);
    }
}
