package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Reclamation;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.ReclamationService;
import com.pidev.backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@AllArgsConstructor
@RequestMapping("/reclamations")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;
    private UserService userService;
    @GetMapping("/get_all")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/get/{id}")
    public Reclamation getReclamationById(@PathVariable String id) {
        return reclamationService.getReclamationById(id);
    }


    @PostMapping("/postV1")
    public Reclamation createReclamation_1(@RequestParam String user_id, @RequestBody Reclamation reclamation) {
        User user = userService.getUserByLogin(user_id);
        reclamation.setUser(user);
        reclamationService.createReclamation(reclamation);
        return reclamation;
    }
    @PostMapping("/postV2")
    public Reclamation createReclamation_2(@RequestBody Reclamation reclamation) {
        System.out.println(reclamation.getDateSubmitted());
        //String user_id = reclamation.getUser().getId();
        //reclamation.setUser(userService.getUserByLogin(user_id));
        //reclamationService.createReclamation(reclamation);
        return reclamation;
    }
    @PutMapping("/put/{id}")
    public Reclamation updateReclamation(@PathVariable String id, @RequestBody Reclamation reclamation) {
        reclamation.setReclamationID(id);
        return reclamationService.updateReclamation(reclamation);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteReclamation(@PathVariable String id) {
        reclamationService.deleteReclamation(id);
    }
    @DeleteMapping("/delete_all")
    public void deleteAllReclamation() {
        reclamationService.deleteAllReclamations();
    }
}
