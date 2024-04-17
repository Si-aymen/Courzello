package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Hashtag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends MongoRepository<Hashtag, String> {
}
