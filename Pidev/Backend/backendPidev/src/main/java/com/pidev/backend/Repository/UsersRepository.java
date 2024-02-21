package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
}
