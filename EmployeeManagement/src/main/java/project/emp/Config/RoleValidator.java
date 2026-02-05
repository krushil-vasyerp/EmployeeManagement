package project.emp.Config;


import jakarta.servlet.http.HttpSession;
import project.emp.ExceptionHandler.RoleException;

public class RoleValidator{

    public static void checkHR(HttpSession session){

        Object role = session.getAttribute("role");

        if(role == null || !role.toString().equalsIgnoreCase("HR")){
            throw new RoleException(
                    "Only HR users can access Salary module"
            );
        }
    }
}
