package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Glossary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GlossaryRepository extends MongoRepository<Glossary, String> {


     //Flux<Glossary> findByTermContainingIgnoreCase(String term);
}
