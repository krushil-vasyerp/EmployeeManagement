package project.emp.Controller.Page;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.emp.Model.Enums.TaskStatus;
import project.emp.Model.Tasks;
import project.emp.Service.EmployeeService;
import project.emp.Service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskPageController {

    private final TaskService taskService;
    private final EmployeeService employeeService;

    public TaskPageController(TaskService taskService,
                              EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("tasks",
                taskService.getTask());

        return "task/task-list";
    }

    @GetMapping("/add")
    public String addPage(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("employees",
                employeeService.getallEmployees());

        model.addAttribute("statuses",
                TaskStatus.values());

        return "task/task-add";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer employeeId,
                       @ModelAttribute Tasks task,
                       HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        taskService.addTask(employeeId, task);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{taskId}")
    public String editPage(@PathVariable Integer taskId,
                           HttpSession session,
                           Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("task",
                taskService.getTaskById(taskId));

        model.addAttribute("statuses",
                TaskStatus.values());

        return "task/task-edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer taskId,
                         @ModelAttribute Tasks task,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        taskService.updateTask(taskId, task);

        return "redirect:/tasks";
    }

    @GetMapping("/delete/{taskId}")
    public String delete(@PathVariable Integer taskId,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        taskService.deleteTask(taskId);

        return "redirect:/tasks";
    }

    @GetMapping("/employee/{employeeId}")
    public String employeeTasks(@PathVariable Integer employeeId,
                                HttpSession session,
                                Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("tasks",
                taskService.getTasksByEmployee(employeeId));

        return "task/employee-task";
    }
}
