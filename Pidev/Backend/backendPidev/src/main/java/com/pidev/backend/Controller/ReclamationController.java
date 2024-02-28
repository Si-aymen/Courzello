package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Attachment;
import com.pidev.backend.Entity.Reclamation;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.AttachementService;
import com.pidev.backend.Service.ReclamationService;
import com.pidev.backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/reclamations")
public class ReclamationController {
    private ReclamationService reclamationService;
    private AttachementService attachment_service;
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
    public Reclamation createReclamation(@RequestBody PostReclamationRequestModel request) {
        Reclamation rec=request.getRec();
        Attachment att=request.getAtt();
        User user=userService.getUserByLogin(rec.getUser().getId());
        rec.setUser(user);
        rec=reclamationService.createReclamation(rec);
        att.setReclamation(rec);
        att=attachment_service.createAttachement(att);
        rec.setAttachment(att);
        return reclamationService.createReclamation(rec);
    }
    @PutMapping("/put/{id}")
    public Reclamation updateReclamation(@PathVariable String id, @RequestBody Reclamation reclamation) {
        reclamation.setReclamationID(id);
        return reclamationService.updateReclamation(reclamation);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteReclamation(@PathVariable String id) {
        Reclamation rec=reclamationService.getReclamationById(id);
        attachment_service.deleteAttachement(rec.getAttachment().getId());
        reclamationService.deleteReclamation(id);
    }
    @DeleteMapping("/delete_all")
    public void deleteAllReclamation() {
        reclamationService.deleteAllReclamations();
    }
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getReclamationStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalReclamations", reclamationService.getTotalReclamations());
        statistics.put("reclamationsByState", reclamationService.getReclamationsByStateCount());

        return ResponseEntity.ok(statistics);
    }
    @GetMapping("/get_by_user")
    public List<Reclamation> getReclamationsByUser(@RequestParam String userId) {
        return reclamationService.getReclamationsByUser(userId);
    }
    @PutMapping("/optimize_priorities")
    public ResponseEntity<String> optimizePriorities() {
        reclamationService.updatePriorities();
        return ResponseEntity.ok("Priorities optimized successfully");
    }
}
