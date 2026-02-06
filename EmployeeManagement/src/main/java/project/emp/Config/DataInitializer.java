package project.emp.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.emp.Model.Departments;
import project.emp.Model.Employees;
import project.emp.Model.Enums.AccountStatus;
import project.emp.Model.Enums.DepartmentTypes;
import project.emp.Model.Enums.Role;
import project.emp.Model.Users;
import project.emp.Repository.DepartmentsRepository;
import project.emp.Repository.EmployeesRepository;
import project.emp.Repository.UsersRepository;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final EmployeesRepository employeesRepository;
    private final DepartmentsRepository departmentsRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsersRepository usersRepository,
                           EmployeesRepository employeesRepository,
                           DepartmentsRepository departmentsRepository,
                           PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.employeesRepository = employeesRepository;
        this.departmentsRepository = departmentsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (usersRepository.count() == 0) {


            Departments department = new Departments();
            department.setName(DepartmentTypes.ADMINISTRATION);
            department = departmentsRepository.save(department);

            Employees employee = new Employees();
            employee.setName("System Admin");
            employee.setEmail("admin@company.com");
            employee.setAddress("Head Office");
            employee.setContact("9999999999");
            employee.setEmergencyContact("8888888888");
            employee.setEmpCode("EMP001");
            employee.setDob(LocalDate.of(1995, 1, 1));
            employee.setJoiningDate(LocalDate.now());
            employee.setDepartment(department);

            employee = employeesRepository.save(employee);

            Users user = new Users();
            user.setUsername("Admin");
            user.setPassword(passwordEncoder.encode("123"));
            user.setRole(Role.ADMIN);
            user.setStatus(AccountStatus.ACTIVE);
            user.setEmployee(employee);

            usersRepository.save(user);

            System.out.println("Default Admin Created");
            System.out.println("Username: Admin");
            System.out.println("Password: 123");
        }else {
            System.out.println("Already User is there");
        }
    }
}
