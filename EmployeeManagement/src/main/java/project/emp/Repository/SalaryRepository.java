package project.emp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.emp.Model.Employees;
import project.emp.Model.Salary;

import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

    boolean existsByEmployee(Employees employee);

    Optional<Salary> findByEmployee(Employees employee);

    @Query( value = "SELECT * FROM salaries ORDER BY net_salary DESC LIMIT 1", nativeQuery = true)
    Salary findHighestSalary();

}
