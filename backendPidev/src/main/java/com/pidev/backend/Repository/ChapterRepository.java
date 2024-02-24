package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChapterRepository extends MongoRepository<Chapter,String> {

}
