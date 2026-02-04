package project.emp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "emp_code"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emp_code", nullable = false, updatable = false)
    private String empCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String contact;
    private String emergencyContact;

    @Column(length = 500)
    private String address;

    private LocalDate dob;

    @Column(nullable = false)
    private LocalDate joiningDate;

    //mappings//list to assign more project to one employee

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Departments department;

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private Users user;

    @OneToOne(mappedBy = "employee" ,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Desk desk;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnore
    private Salary salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tasks> tasks;
}
