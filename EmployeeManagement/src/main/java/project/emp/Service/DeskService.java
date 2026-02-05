package project.emp.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emp.ExceptionHandler.BadRequestException;
import project.emp.ExceptionHandler.ResourceNotFoundException;
import project.emp.Model.Desk;
import project.emp.Model.Employees;
import project.emp.Repository.DeskRepository;
import project.emp.Repository.EmployeesRepository;

import java.util.List;

@Service
public class DeskService {

    @Autowired
    private DeskRepository deskrepo;
    @Autowired
    private EmployeesRepository emprepo;

    public List<Desk> findAllDesk() {
        return deskrepo.findAll();
    }

    public Desk findDeskById(Integer id) {
        return deskrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Desk not found with id: " + id));
    }

    public Desk addDesk(Desk desk) {
        return deskrepo.save(desk);
    }

    public Desk updateDesk(Desk desk , Integer id) {
        Desk existing = findDeskById(id);
        desk.setId(existing.getId());
        return deskrepo.save(desk);
    }
    @Transactional
    public void deleteDesk(Integer id) {
        Desk desk = deskrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Desk not found"));
        if(desk.getEmployee() != null){
            desk.getEmployee().setDesk(null);
            desk.setEmployee(null);
        }

        deskrepo.delete(desk);
    }
    //related with employee for resource allocation
    public Desk assignDeskToEmployee(Integer deskId, Integer employeeId) {
        Desk desk = findDeskById(deskId);
        if(!desk.getAvailable()){
            throw new ResourceNotFoundException("Desk" + deskId+" is not available");
        }
        Employees employee = emprepo.findById(employeeId).orElseThrow();
        //assign a desk
        desk.setEmployee(employee);
        desk.setAvailable(false);
        return deskrepo.save(desk);
    }

    public Desk unassignDeskFromEmployee(Integer deskId) {
        Desk desk = findDeskById(deskId);
        if(desk.getEmployee() == null){
            throw new BadRequestException("Desk is already unassigned");
        }
        desk.setEmployee(null);
        desk.setAvailable(true);
        return deskrepo.save(desk);
    }

    public List<Desk> getAvailableDesks() {
        return deskrepo.findByAvailableTrue();
    }
}
