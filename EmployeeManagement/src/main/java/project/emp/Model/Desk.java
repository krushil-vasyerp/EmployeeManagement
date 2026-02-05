package project.emp.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import project.emp.Model.Enums.DeskType;

@Data
@Entity
@Table(
        name = "desks",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "desk_code")
        }
)
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "desk_code", nullable = false, unique = true)
    private String deskCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeskType deskType;

    @Column(nullable = false)
    private Boolean available = true;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    @JsonManagedReference
    private Employees employee;

}
