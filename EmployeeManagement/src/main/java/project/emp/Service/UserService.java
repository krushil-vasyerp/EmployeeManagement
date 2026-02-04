package project.emp.Service;


import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.emp.ExceptionHandler.ResourceNotFoundException;
import project.emp.Model.Employees;
import project.emp.Model.Users;
import project.emp.Repository.EmployeesRepository;
import project.emp.Repository.UsersRepository;

import java.util.List;

@Service
public class UserService {

    private UsersRepository urepo;
    private EmployeesRepository erepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, EmployeesRepository erepo, UsersRepository urepo) {
        this.passwordEncoder = passwordEncoder;
        this.erepo = erepo;
        this.urepo = urepo;
    }

    public List<Users> getAllUsers() {
        return urepo.findAll();
    }

    public Users getUserById(Integer id) {
        return urepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public void deleteUser(Integer userId) {
        Users user = getUserById(userId);

        Employees employee = user.getEmployee();

        user.setEmployee(null);
        employee.setUser(null);

        urepo.delete(user);
    }


    public Users createUser(Integer employeeId, Users user) {

        Employees employee = erepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (urepo.existsByEmployee(employee)) {
            throw new ResourceNotFoundException("User already exists for this employee");
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        user.setEmployee(employee);
        return urepo.save(user);
    }

    public Users updateUser(Integer userId, Users user) {
        Users existing = getUserById(userId);
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        user.setId(existing.getId());
        user.setEmployee(existing.getEmployee());
        return urepo.save(user);
    }

    public List<Users> findActiveUsers(){
        return urepo.findActiveUsers();
    };

}
