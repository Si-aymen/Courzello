package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Conversation;
import com.pidev.backend.Entity.Course;
import com.pidev.backend.Service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseService courseService ;


    @PostMapping("/add-course")
    public Course addCourse(@RequestBody Course c) {
        return courseService.addCourse(c);
    }

    @GetMapping("/retrieve-courses")
    public List<Course> getAllCourses() {
        return courseService.getALLCourses();
    }

    @PutMapping ("/modify-course")
    public Course modifyCourse(@RequestBody Course c) {
        return courseService.modifyCourse(c);
    }
    @DeleteMapping("/delete-course/{course-id}")
    public void deleteCourse(@PathVariable("course-id") String courseId) {
courseService.deleteCourse(courseId);
    }

}
