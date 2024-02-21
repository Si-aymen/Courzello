package com.pidev.backend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.pidev.backend.Entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
