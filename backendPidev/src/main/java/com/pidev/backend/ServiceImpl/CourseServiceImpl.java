package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Course;
import com.pidev.backend.Repository.CourseRepository;
import com.pidev.backend.Service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository ;
    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getALLCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course modifyCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(String CourseId) {
        Course c = courseRepository.findById(CourseId).get();
        courseRepository.delete(c);

    }
}
