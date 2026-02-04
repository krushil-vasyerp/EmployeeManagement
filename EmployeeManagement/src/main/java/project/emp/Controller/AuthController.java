package project.emp.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.emp.Jwt.JwtUtil;
import project.emp.Model.Users;
import project.emp.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService aservice;
    private final JwtUtil jwtUtil;

    //constructor
    public AuthController(AuthService aservice, JwtUtil jwtUtil) {
        this.aservice = aservice;
        this.jwtUtil = jwtUtil;
    }

    //fist to access all other returns jwt
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Users user = aservice.login(username, password);
        return jwtUtil.generateToken(user.getUsername());
    }

}
