package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Glossary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GlossaryRepository extends MongoRepository<Glossary, String> {
}
