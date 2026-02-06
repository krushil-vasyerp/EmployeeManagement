package project.emp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.emp.Model.Departments;
import project.emp.Model.Employees;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    boolean existsByDepartment(Departments dept);

    Optional<Employees> findByDepartment(Departments realDept);

    Optional<Employees> findFirstByDepartment(Departments newDept);
}
