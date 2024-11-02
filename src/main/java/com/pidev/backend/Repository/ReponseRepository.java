package com.pidev.backend.Repository;


import com.pidev.backend.Entity.Reponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepository extends MongoRepository<Reponse, String> {
}
