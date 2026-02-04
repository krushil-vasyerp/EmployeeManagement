package project.emp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.emp.ExceptionHandler.BadRequestException;
import project.emp.Model.Users;
import project.emp.Repository.UsersRepository;

@Service
public class AuthService {

    @Autowired
    private UsersRepository urepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public Users login(String username, String password) {

        //make single user that fetched from the credential from db using username
        Users user = urepo.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("Invalid username"));

        //match the both db and entered password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        if (user.getStatus().name().equals("INACTIVE")) {
            throw new BadRequestException("User account is inactive");
        }

        return user;
    }
}
