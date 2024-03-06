package com.pidev.backend.Repository;


import com.pidev.backend.Entity.FileDB;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileDBRepository extends MongoRepository<FileDB, String> {
	

}
