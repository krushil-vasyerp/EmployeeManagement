package project.emp.Controller.Page;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.emp.Model.Desk;
import project.emp.Model.Employees;
import project.emp.Model.Enums.DeskType;
import project.emp.Service.DeskService;
import project.emp.Service.EmployeeService;

@Controller
@RequestMapping("/desks")
public class DeskPageController {

    private final DeskService deskService;
    private final EmployeeService employeeService;

    public DeskPageController(DeskService deskService,
                              EmployeeService employeeService) {
        this.deskService = deskService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("desks",
                deskService.findAllDesk());

        return "desk/desk-list";
    }

    @GetMapping("/add")
    public String addPage(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("types", DeskType.values());

        return "desk/desk-add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Desk desk,
                       HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        deskService.addDesk(desk);

        return "redirect:/desks";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id,
                           HttpSession session,
                           Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("desk",
                deskService.findDeskById(id));
        model.addAttribute("types", DeskType.values());

        return "desk/desk-edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Desk desk,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        deskService.updateDesk(desk, desk.getId());

        return "redirect:/desks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        deskService.deleteDesk(id);

        return "redirect:/desks";
    }

    @GetMapping("/assign/{deskId}")
    public String assignPage(@PathVariable Integer deskId,
                             HttpSession session,
                             Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("deskId", deskId);
        model.addAttribute("employees",
                employeeService.getallEmployees());

        return "desk/desk-assign";
    }

    @PostMapping("/assign")
    public String assign(@RequestParam Integer deskId,
                         @RequestParam Integer employeeId,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        deskService.assignDeskToEmployee(deskId, employeeId);

        return "redirect:/desks";
    }

    @GetMapping("/unassign/{deskId}")
    public String unassign(@PathVariable Integer deskId,
                           HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        deskService.unassignDeskFromEmployee(deskId);

        return "redirect:/desks";
    }
}
