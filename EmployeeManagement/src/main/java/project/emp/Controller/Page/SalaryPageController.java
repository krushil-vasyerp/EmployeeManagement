package project.emp.Controller.Page;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.emp.Model.Salary;
import project.emp.Service.EmployeeService;
import project.emp.Service.SalaryService;

@Controller
@RequestMapping("/salary")
public class SalaryPageController {

    private final SalaryService salaryService;
    private final EmployeeService employeeService;

    public SalaryPageController(SalaryService salaryService,
                                EmployeeService employeeService) {
        this.salaryService = salaryService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("salaries",
                salaryService.getAllSalaries());

        return "salary/salary-list";
    }

    @GetMapping("/add")
    public String addPage(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("employees",
                employeeService.getallEmployees());

        return "salary/salary-add";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer employeeId,
                       @ModelAttribute Salary salary,
                       HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        salaryService.addSalary(employeeId, salary);

        return "redirect:/salary";
    }

    @GetMapping("/edit/{employeeId}")
    public String editPage(@PathVariable Integer employeeId,
                           HttpSession session,
                           Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("salary",
                salaryService.getSalaryByEmpId(employeeId));

        model.addAttribute("employeeId", employeeId);

        return "salary/salary-edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer employeeId,
                         @ModelAttribute Salary salary,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        salaryService.updateSalary(employeeId, salary);

        return "redirect:/salary";
    }

    @GetMapping("/delete/{employeeId}")
    public String delete(@PathVariable Integer employeeId,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        salaryService.deleteSalary(employeeId);

        return "redirect:/salary";
    }
}
