package com.pidev.backend.ServiceImpl;


import com.pidev.backend.Entity.Glossary;
import com.pidev.backend.Repository.GlossaryRepository;
import com.pidev.backend.Service.GlossaryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor

public class GlossaryServiceImpl implements GlossaryService {
    @Autowired
    private GlossaryRepository glossaryRepository;
    @Override
    public List<Glossary> getAllTermes() {
        return glossaryRepository.findAll();
    }
    @Override

    public Glossary getGlossaryById(String id) {
        return glossaryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Glossary term not found with id: " + id));
    }
    @Override

    public Glossary addGlossary(Glossary glossary) {
        return glossaryRepository.save(glossary);
    }
    @Override

    public Glossary updateGlossary(String id, Glossary newGlossary) {
        Glossary existingGlossary = getGlossaryById(id);
        existingGlossary.setTerm(newGlossary.getTerm());
        existingGlossary.setDefinition(newGlossary.getDefinition());
        return glossaryRepository.save(existingGlossary);
    }
    @Override

    public void deleteGlossary(String id) {
        Glossary existingGlossary = getGlossaryById(id);
        glossaryRepository.delete(existingGlossary);
    }
  /* @Override
    public Flux<Glossary> rechercheEnTempsReel(String term) {
        return glossaryRepository.findByTermContainingIgnoreCase(term)
                .delayElements(Duration.ofMillis(500)); // Délai simulé pour l'exemple
    }*/

}
