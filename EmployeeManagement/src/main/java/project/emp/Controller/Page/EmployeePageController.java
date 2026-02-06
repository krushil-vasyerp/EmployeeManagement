package project.emp.Controller.Page;

import project.emp.Model.Departments;
import project.emp.Model.Employees;
import project.emp.Service.DepartmentService;
import project.emp.Service.EmployeeService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeePageController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeePageController(EmployeeService employeeService,
                                  DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }


    @GetMapping
    public String employeeList(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("employees",
                employeeService.getallEmployees());

        return "employee/employee-list";
    }

    @GetMapping("/add")
    public String addEmployeePage(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("departments",
                departmentService.getAllDepartments());

        return "employee/employee-add";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employees employee,
                               @RequestParam Integer departmentId,
                               HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        Departments department = new Departments();
        department.setId(departmentId);
        employee.setDepartment(department);

        employeeService.addEmployee(employee);

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id,
                               HttpSession session,
                               Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("employee",
                employeeService.findEmployeeById(id));

        model.addAttribute("departments",
                departmentService.getAllDepartments());

        return "employee/employee-edit";
    }


    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employees employee,
                                 @RequestParam Integer departmentId,
                                 HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        Departments department = new Departments();
        department.setId(departmentId);
        employee.setDepartment(department);

        employeeService.updateEmployee(employee, employee.getId());

        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id,
                                 HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }
}
