package project.emp.Controller.Page;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.emp.Model.Enums.AccountStatus;
import project.emp.Model.Enums.Role;
import project.emp.Model.Users;
import project.emp.Service.EmployeeService;
import project.emp.Service.UserService;

@Controller
@RequestMapping("/users")
public class UserPageController {

    private final UserService userService;
    private final EmployeeService employeeService;

    public UserPageController(UserService userService,
                              EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("users",
                userService.getAllUsers());

        return "user/user-list";
    }

    @GetMapping("/add")
    public String addPage(HttpSession session, Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("employees",
                employeeService.getallEmployees());

        model.addAttribute("roles",
                Role.values());

        model.addAttribute("statuses",
                AccountStatus.values());

        return "user/user-add";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer employeeId,
                       @ModelAttribute Users user,
                       HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        userService.createUser(employeeId, user);

        return "redirect:/users";
    }

    @GetMapping("/edit/{userId}")
    public String editPage(@PathVariable Integer userId,
                           HttpSession session,
                           Model model) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        model.addAttribute("user",
                userService.getUserById(userId));

        model.addAttribute("roles",
                Role.values());

        model.addAttribute("statuses",
                AccountStatus.values());

        return "user/user-edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer userId,
                         @ModelAttribute Users user,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        userService.updateUser(userId, user);

        return "redirect:/users";
    }

    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable Integer userId,
                         HttpSession session) {

        if (session.getAttribute("user") == null)
            return "redirect:/";

        userService.deleteUser(userId);

        return "redirect:/users";
    }
}
