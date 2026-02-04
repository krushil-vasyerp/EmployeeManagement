package project.emp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.emp.Model.Departments;

import java.util.List;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {

    @Query(
            value = """
            SELECT d.name AS department_name, COUNT(e.id) AS employee_count
            FROM departments d LEFT JOIN employees e ON e.department_id = d.id
            GROUP BY d.id, d.name ORDER BY d.id""", nativeQuery = true
    )
    List<?> findDepartmentsWithEmployees();

}
