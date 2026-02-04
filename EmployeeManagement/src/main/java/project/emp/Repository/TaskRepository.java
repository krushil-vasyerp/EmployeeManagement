package project.emp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.emp.Model.Employees;
import project.emp.Model.Tasks;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findByEmployee(Employees employee);

    @Query(value = "SELECT * FROM tasks WHERE status = :status", nativeQuery = true)
    List<Tasks> findByStatus(@Param("status") String status);

}
