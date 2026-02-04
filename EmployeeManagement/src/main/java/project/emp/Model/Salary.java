package project.emp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "salaries",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"employee_id"})
        }
)
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double basicSalary;

    private Double bonus;

    private Double deduction;

    @Column(nullable = false)
    private Double netSalary;

    //mappings
    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    @JsonIgnore
    private Employees employee;
}
