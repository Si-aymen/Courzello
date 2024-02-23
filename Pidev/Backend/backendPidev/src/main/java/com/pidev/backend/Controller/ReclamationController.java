package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Reclamation;
import com.pidev.backend.Service.ReclamationService;
import com.pidev.backend.Service.UserService;
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
    private UserService userService;
    @GetMapping("/get_all")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/get/{id}")
    public Reclamation getReclamationById(@PathVariable String id) {
        return reclamationService.getReclamationById(id);
    }
    @PostMapping("/post")
    public Reclamation createReclamation_2(@RequestBody Reclamation reclamation) {
        String user_id = reclamation.getUser().getId();
        reclamation.setUser(userService.getUserByLogin(user_id));

        // Appel de la méthode generateAutomaticResponse
        reclamation.setAutomaticResponse(reclamation.generateAutomaticResponse());

        // Création de la réclamation
        reclamationService.createReclamation(reclamation);

        // Retour de la réclamation, y compris la réponse automatique
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
