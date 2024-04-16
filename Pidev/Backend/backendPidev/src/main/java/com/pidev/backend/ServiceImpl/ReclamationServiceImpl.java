package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Attachment;
import com.pidev.backend.Entity.Reclamation;
import com.pidev.backend.Entity.ReclamtionState;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.ReclamationRepository;
import com.pidev.backend.Service.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReclamationServiceImpl implements ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;
    private ReclamationRepository att_repo;
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
        Attachment att=reclamation.getAttachment();
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void deleteAllReclamations() {
        reclamationRepository.deleteAll();
    }

    @Override
    public long getTotalReclamations() {
        return reclamationRepository.count();
    }

    @Override
    public Map<ReclamtionState, Long> getReclamationsByStateCount() {
        List<Reclamation> reclamations = reclamationRepository.findAll();

        return reclamations.stream()
                .filter(reclamation -> reclamation.getState() != null)
                .collect(Collectors.groupingBy(Reclamation::getState, Collectors.counting()));
    }
    @Override
    public List<Reclamation> getReclamationsByUser(String userId) {
        return reclamationRepository.findByUser_Id(userId);
    }



    @Override
    public void updatePriorities() {
        List<Reclamation> reclamations = getAllReclamations();

        for (Reclamation reclamation : reclamations) {
            int newPriority = calculatePriority(reclamation);
            reclamation.setPriority(newPriority);
            updateReclamation(reclamation);
        }
    }

    private int calculatePriority(Reclamation reclamation) {
        int basePriority = 50;
        double weightResolutionTime = 0.2;
        double weightUserImpact = 0.8;

        int resolutionTimeInHours = calculateResolutionTimeInHours(reclamation);
        int userImpact = reclamation.getUserImpact();

        int newPriority = basePriority + (int) (weightResolutionTime * resolutionTimeInHours + weightUserImpact * userImpact);

        return Math.min(Math.max(newPriority, 0), 1000);
    }

    private int calculateResolutionTimeInHours(Reclamation reclamation) {
        // Exemple de logique pour calculer le délai de résolution en heures
        LocalDateTime creationDate = reclamation.getCreationDate();
        LocalDateTime expectedResolutionDate = reclamation.getExpectedResolutionDate();

        long resolutionTimeInHours = ChronoUnit.HOURS.between(creationDate, expectedResolutionDate);

        return Math.toIntExact(resolutionTimeInHours);
    }

    @Override
    public double getAverageResolutionTime() {
        List<Reclamation> reclamations = getAllReclamations();

        if (reclamations.isEmpty()) {
            return 0.0; // Évitez une division par zéro
        }

        long totalResolutionTime = reclamations.stream()
                .mapToLong(this::calculateResolutionTimeInHours)
                .sum();

        return (double) totalResolutionTime / reclamations.size();
    }

    @Override
    public long getPendingReclamationsCount() {
        return getAllReclamations().stream()
                .filter(reclamation -> ReclamtionState.EN_ATTENTE.equals(reclamation.getState()))
                .count();
    }

}
