package com.pidev.backend.Service;

import com.pidev.backend.Entity.Reclamation;
import com.pidev.backend.Entity.User;

import java.util.List;

public interface ReclamationService {
    List<Reclamation> getAllReclamations();
    Reclamation getReclamationById(String reclamationID);
    Reclamation createReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Reclamation reclamation);
    void deleteReclamation(String id);
    void deleteAllReclamations();
    String generateAutomaticResponse(String reclamationType, String categorieReclamation);
    ;
}
