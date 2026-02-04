package project.emp.Service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.emp.Config.UserPrincipal;
import project.emp.ExceptionHandler.ResourceNotFoundException;
import project.emp.Model.Users;
import project.emp.Repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository urepo;
    public CustomUserDetailsService(UsersRepository urepo) {
        this.urepo = urepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)//used jwt filter for load the data of user by username
            throws UsernameNotFoundException {

        Users user = urepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return new UserPrincipal(user);
    }
}
