package project.emp.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.emp.Model.Employees;
import project.emp.Model.Enums.Role;
import project.emp.Model.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByEmployee(Employees employee);
    Optional<Users> findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE status = 'ACTIVE'", nativeQuery = true)
    List<Users> findActiveUsers();

    boolean existsByRole(Role role);
}
