package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Attachment;
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
}
