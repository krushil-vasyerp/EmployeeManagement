package project.emp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import project.emp.Model.Enums.DepartmentTypes;

import java.util.List;

@Data
@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private DepartmentTypes name;

    //mappings//list to store many employee
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employees> employees;
}
