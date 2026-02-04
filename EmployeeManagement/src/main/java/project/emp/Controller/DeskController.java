package project.emp.Controller;

import org.springframework.web.bind.annotation.*;
import project.emp.Model.Desk;
import project.emp.Service.DeskService;

import java.util.List;

@RestController
@RequestMapping("/desk")
public class DeskController {

    private DeskService dservice;
    //constructor
    public DeskController(DeskService dservice) {
        this.dservice = dservice;
    }

    @GetMapping
    public List<Desk> findAll(){
        return dservice.findAllDesk();
    }

    //use for finding where is this desk or all desk
    @GetMapping("/{id}")
    public Desk findById(@PathVariable Integer id){
        return dservice.findDeskById(id);
    }

    @PostMapping
    public Desk addDesk(@RequestBody Desk desk){
        return dservice.addDesk(desk);
    }

    //to upgrade or edit desk details
    @PutMapping("/{id}")
    public Desk updateDesk(@RequestBody Desk desk , @PathVariable Integer id){
        return dservice.updateDesk(desk , id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        dservice.deleteDesk(id);
    }

    //employee related desk operation of assign and unassign
    @PutMapping("/{deskId}/assign/{employeeId}")
    public Desk assignDesk(@PathVariable Integer deskId, @PathVariable Integer employeeId) {
        return dservice.assignDeskToEmployee(deskId, employeeId);
    }

    @PutMapping("/{deskId}/unassign")
    public Desk unassignDesk(@PathVariable Integer deskId) {
        return dservice.unassignDeskFromEmployee(deskId);
    }

    @GetMapping("/available")
    public List<Desk> getAvailableDesks() {
        return dservice.getAvailableDesks();
    }

}
