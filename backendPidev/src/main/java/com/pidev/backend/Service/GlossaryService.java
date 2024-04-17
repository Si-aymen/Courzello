package com.pidev.backend.Service;

import com.pidev.backend.Entity.Glossary;

import java.util.List;

public interface GlossaryService {
    List<Glossary> getAllTermes();
    Glossary getGlossaryById(String id);
    Glossary addGlossary(Glossary glossary);
    Glossary updateGlossary(String id, Glossary glossary);
    void deleteGlossary(String id);

   // Flux<Glossary> rechercheEnTempsReel(String term);

}
