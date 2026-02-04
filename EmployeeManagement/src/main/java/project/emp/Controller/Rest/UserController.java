package project.emp.Controller.Rest;


import org.springframework.web.bind.annotation.*;
import project.emp.Model.Users;
import project.emp.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService uservice;
    //constructor
    public UserController(UserService uservice) {
        this.uservice = uservice;
    }

    //read user data and delete user
    @GetMapping
    public List<Users> getAllUsers() {
        return uservice.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Integer id) {
        return uservice.getUserById(id);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        uservice.deleteUser(userId);
    }

    //assign, update new user or role to employee
    @PostMapping("/employee/{employeeId}")
    public Users createUser(
            @PathVariable Integer employeeId,
            @RequestBody Users user) {
        return uservice.createUser(employeeId, user);
    }

    @PutMapping("/{userId}")
    public Users updateUser(
            @PathVariable Integer userId,
            @RequestBody Users user) {
        return uservice.updateUser(userId, user);
    }

    @GetMapping("/ACTIVE")
    public List<Users> findActiveUsers(){
        return uservice.findActiveUsers();
    }
}
