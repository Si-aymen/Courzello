package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Attachment;
import com.pidev.backend.Entity.Notification;
import com.pidev.backend.Entity.Reclamation;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.AttachementService;
import com.pidev.backend.Service.NotificationService;
import com.pidev.backend.Service.ReclamationService;
import com.pidev.backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )



@RestController
@AllArgsConstructor
@RequestMapping("/reclamations")
public class ReclamationController {
    private ReclamationService reclamationService;
    private AttachementService attachment_service;
    private UserService userService;
    private NotificationService notificationService;

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
        Reclamation rec = request.getRec();
        Attachment att = request.getAtt();

        // Assurez-vous que l'objet User est correctement associé à la réclamation
        User user = userService.getUserByLogin(rec.getUser().getId());
        rec.setUser(user);

        // Créez la réclamation
        rec = reclamationService.createReclamation(rec);

        // Assurez-vous que l'objet Attachment est présent avant de l'associer à la réclamation
        if (att != null) {
            att.setReclamation(rec);
            att = attachment_service.createAttachement(att);
            rec.setAttachment(att);
        }

        // Envoyez une notification à l'utilisateur concerné
        sendNotificationToUser(user.getId(), "Votre réclamation a été créée avec succès!");

        return rec;
    }

    private void sendNotificationToUser(String userId, String message) {
        // Utilisez votre service de notification pour envoyer la notification
        notificationService.sendNotification(userId, new Notification(message));
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<Reclamation> updateReclamation(@PathVariable String id, @RequestBody Reclamation updatedReclamation) {

        // Perform input validation to check if the provided ID is valid
        Reclamation existingReclamation = reclamationService.getReclamationById(id);


        // Update the Reclamation
        Reclamation updatedRec = reclamationService.updateReclamation(updatedReclamation);

        return ResponseEntity.ok(updatedRec);

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReclamation(@PathVariable String id) {
        try {
            // Retrieve the Reclamation by ID
            Reclamation rec = reclamationService.getReclamationById(id);

            if (rec != null) {
                // Retrieve the Attachment from the Reclamation
                Attachment attachment = rec.getAttachment();

                // Delete the Attachment if it exists
                if (attachment != null) {
                    attachment_service.deleteAttachement(attachment.getId());
                }

                // Delete the Reclamation
                reclamationService.deleteReclamation(id);

                // Send a notification to the user that the Reclamation has been deleted
                sendNotificationToUser(rec.getUser().getId(), "Votre réclamation a été supprimée avec succès.");

                return ResponseEntity.ok("Reclamation deleted successfully");
            } else {
                // Handle the case where Reclamation with the given ID is not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamation not found with ID: " + id);
            }
        } catch (Exception e) {
            // Handle any other exceptions that might occur during deletion
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Reclamation");
        }
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

    @GetMapping("/advanced_statistics")
    public ResponseEntity<Map<String, Object>> getAdvancedStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("averageResolutionTime", reclamationService.getAverageResolutionTime());
        statistics.put("pendingReclamationsCount", reclamationService.getPendingReclamationsCount());
        // Ajoutez d'autres métriques en fonction de vos besoins

        return ResponseEntity.ok(statistics);
    }
}