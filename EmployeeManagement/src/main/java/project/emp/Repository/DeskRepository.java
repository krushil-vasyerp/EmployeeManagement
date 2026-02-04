package project.emp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.emp.Model.Desk;

import java.util.List;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Integer> {

    @Query(value = "SELECT * FROM desks WHERE available = true", nativeQuery = true)
    List<Desk> findByAvailableTrue();

}
