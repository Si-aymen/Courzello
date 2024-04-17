package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Question;
import com.pidev.backend.Entity.SignalBadword;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignaBAdWordRepository extends MongoRepository<SignalBadword, String> {
}
