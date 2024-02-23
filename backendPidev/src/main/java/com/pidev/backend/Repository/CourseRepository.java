package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
