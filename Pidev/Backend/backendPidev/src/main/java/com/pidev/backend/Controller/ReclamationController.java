package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Reclamation;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Entity.Users;
import com.pidev.backend.Service.ReclamationService;
import com.pidev.backend.Service.UserService;
import com.pidev.backend.Service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reclamations")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;
    private UsersService userService;
    @GetMapping("/getall")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/get/{id}")
    public Reclamation getReclamationById(@PathVariable String id) {
        return reclamationService.getReclamationById(id);
    }

    @PostMapping("/post/{login}")
    public Reclamation createReclamation(@RequestBody Reclamation reclamation, @PathVariable String login) {
        Users user=userService.getUserByLogin(login);
        reclamation.setUser(user);
        reclamationService.createReclamation(reclamation);
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
