package project.emp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.emp.Model.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
}
