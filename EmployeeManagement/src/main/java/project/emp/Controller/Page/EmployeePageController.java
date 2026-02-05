package project.emp.Controller.Page;

import project.emp.Model.Departments;
import project.emp.Model.Employees;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/employees")
public class EmployeePageController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String EMP_URL = "http://localhost:9090/employee";
    private final String DEPT_URL = "http://localhost:9090/department";

    @GetMapping
    public String employeeList(HttpSession session, Model model) {

        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        Employees[] employees = restTemplate.exchange(
                EMP_URL,
                HttpMethod.GET,
                entity,
                Employees[].class
        ).getBody();

        model.addAttribute("employees", employees);

        return "employee/employee-list";
    }

    @GetMapping("/add")
    public String addEmployeePage(HttpSession session, Model model) {

        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        Departments[] departments = restTemplate.exchange(
                DEPT_URL,
                HttpMethod.GET,
                entity,
                Departments[].class
        ).getBody();

        model.addAttribute("departments", departments);

        return "employee/employee-add";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employees employee,
                               @RequestParam Integer departmentId,
                               HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/";

        Departments department = new Departments();
        department.setId(departmentId);
        employee.setDepartment(department);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Employees> entity = new HttpEntity<>(employee, headers);

        restTemplate.exchange(
                EMP_URL,
                HttpMethod.POST,
                entity,
                Employees.class
        );

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id,
                               HttpSession session,
                               Model model) {

        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        Employees employee = restTemplate.exchange(
                EMP_URL + "/" + id,
                HttpMethod.GET,
                entity,
                Employees.class
        ).getBody();

        Departments[] departments = restTemplate.exchange(
                DEPT_URL,
                HttpMethod.GET,
                entity,
                Departments[].class
        ).getBody();

        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);

        return "employee/employee-edit";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employees employee,
                                 @RequestParam Integer departmentId,
                                 HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/";

        Departments department = new Departments();
        department.setId(departmentId);
        employee.setDepartment(department);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Employees> entity = new HttpEntity<>(employee, headers);

        restTemplate.exchange(
                EMP_URL + "/" + employee.getId(),
                HttpMethod.PUT,
                entity,
                Employees.class
        );

        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id,
                                 HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                EMP_URL + "/" + id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
        return "redirect:/employees";
    }
}
