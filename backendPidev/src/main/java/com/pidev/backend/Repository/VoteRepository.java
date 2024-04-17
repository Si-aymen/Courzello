package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Question;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Entity.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {
    Vote findByQuestionAndUser(String idquestion, String idUser);
}
