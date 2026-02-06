package project.emp.Controller.Page;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.emp.Model.Departments;
import project.emp.Model.Enums.DepartmentTypes;
import project.emp.Service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentPageController {

    private final DepartmentService departmentService;

    public DepartmentPageController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String departmentList(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("departments",
                departmentService.getAllDepartments());

        return "department/department-list";
    }

    @GetMapping("/add")
    public String addDepartmentPage(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("types", DepartmentTypes.values());

        return "department/department-add";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute Departments dept,
                                 HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        departmentService.addDepartment(dept);

        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable Integer id,
                                 HttpSession session,
                                 Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("department",
                departmentService.getDepartmentById(id));

        return "department/department-edit";
    }

    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Departments dept,
                                   HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        departmentService.updateDepartment(dept.getId(), dept);

        return "redirect:/departments";
    }

    @GetMapping("/check")
    @ResponseBody
    public String check(HttpSession session) {
        System.out.println(session.getId());
        return session.getId();
    }


    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id,
                                   HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        departmentService.deleteDepartment(id);

        return "redirect:/departments";
    }
}
