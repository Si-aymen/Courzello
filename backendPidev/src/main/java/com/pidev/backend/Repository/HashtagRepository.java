package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Hashtag;
import com.pidev.backend.Entity.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends MongoRepository<Hashtag, String> {
}
