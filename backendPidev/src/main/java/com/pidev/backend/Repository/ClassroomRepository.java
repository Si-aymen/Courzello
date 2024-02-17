package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends MongoRepository<Classroom, Long> {
    Classroom getClassroomByClassroomLevel(String lvl );
}
