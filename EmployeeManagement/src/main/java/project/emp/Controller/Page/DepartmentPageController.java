package project.emp.Controller.Page;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.emp.Model.Departments;
import project.emp.Model.Enums.DepartmentTypes;

@Controller
@RequestMapping("/departments")
public class DepartmentPageController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:9090/department";

    @GetMapping
    public String departmentList(HttpSession session, Model model) {

        String token = (String) session.getAttribute("token");

        if (token == null)
            return "redirect:/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Departments[]> response =
                restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, Departments[].class);

        model.addAttribute("departments", response.getBody());

        return "department/department-list";
    }

    @GetMapping("/add")
    public String addDepartmentPage(HttpSession session, Model model) {

        if (session.getAttribute("token") == null)
            return "redirect:/";

        model.addAttribute("types", DepartmentTypes.values());

        return "department/department-add";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute Departments dept, HttpSession session) {

        String token = (String) session.getAttribute("token");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Departments> entity = new HttpEntity<>(dept, headers);

        restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, Departments.class);

        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable Integer id,
                                 HttpSession session,
                                 Model model) {

        String token = (String) session.getAttribute("token");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Departments> response =
                restTemplate.exchange(BASE_URL + "/" + id,
                        HttpMethod.GET,
                        entity,
                        Departments.class);

        model.addAttribute("department", response.getBody());

        return "department/department-edit";
    }

    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Departments dept,
                                   HttpSession session) {

        String token = (String) session.getAttribute("token");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Departments> entity = new HttpEntity<>(dept, headers);

        restTemplate.exchange(BASE_URL + "/" + dept.getId(),
                HttpMethod.PUT,
                entity,
                Departments.class);

        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id,
                                   HttpSession session) {

        String token = (String) session.getAttribute("token");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        restTemplate.exchange(BASE_URL + "/" + id,
                HttpMethod.DELETE,
                entity,
                Void.class);

        return "redirect:/departments";
    }
}
