package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Reclamation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends MongoRepository<Reclamation, String> {}
