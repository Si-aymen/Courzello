package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Course;
import com.pidev.backend.Entity.Speciality;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> getCourseByCourseDomain(Speciality speciality);
}
