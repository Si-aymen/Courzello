package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachRepository extends MongoRepository<Attachment, String> {}
