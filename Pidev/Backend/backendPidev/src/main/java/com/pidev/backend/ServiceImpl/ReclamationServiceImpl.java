package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Reclamation;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.ReclamationRepository;
import com.pidev.backend.Service.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReclamationServiceImpl implements ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;
    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation getReclamationById(String id) {
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);
        return optionalReclamation.orElse(new Reclamation());
    }

    @Override
    public void deleteReclamation(String id) {
        reclamationRepository.deleteById(id);
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation createReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void deleteAllReclamations() {
        reclamationRepository.deleteAll();
    }
    @Override
    public String generateAutomaticResponse(String reclamationType, String categorieReclamation) {
        // Logique de génération de réponse automatique en fonction des critères
        // Vous pouvez utiliser la logique que vous avez implémentée dans la méthode getPredefinedResponse du contrôleur
        // ou vous pouvez implémenter une nouvelle logique ici.

        // Exemple:
        if ("INCIDENT".equals(reclamationType) && "TECHNICAL".equals(categorieReclamation)) {
            return "Thank you for reporting the technical incident. Our team is working on resolving it.";
        } else if ("SUGGESTION".equals(reclamationType) && "QUALITY".equals(categorieReclamation)) {
            return "We appreciate your suggestion for improving quality. It will be considered in our future enhancements.";
        } else {
            // Retourner null si aucune réponse prédéfinie n'est trouvée pour les critères donnés
            return null;
        }
    }
}
