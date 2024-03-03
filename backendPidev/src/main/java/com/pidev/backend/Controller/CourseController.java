package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Course;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/GetCourseById/{course-id}")
    public Course getAllCourses(@PathVariable("course-id") String courseid) {
        return courseService.getCourseById(courseid);
    }

    @PutMapping ("/modify-course")
    public Course modifyCourse(@RequestBody Course c) {
        return courseService.modifyCourse(c);
    }

    @DeleteMapping("/delete-course/{course-id}")
    public void deleteCourse(@PathVariable("course-id") String courseId) {
courseService.deleteCourse(courseId);
    }

    @PutMapping("/course-UploadPDF/{course-id}")
    public void uploadPDF(@PathVariable("course-id") String courseId, @RequestParam("file") MultipartFile file) {
        courseService.uploadPdf(file, courseId);
    }


    @GetMapping("/classroom/{classroomId}")
    public List<Course> getCoursesByClassroom(@PathVariable String classroomId) {
        List<Course> courses = courseService.getCoursessByClassroom(classroomId);
        return  courses ;
    }


}
