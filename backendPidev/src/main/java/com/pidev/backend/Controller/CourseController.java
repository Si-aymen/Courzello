package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Course;
import com.pidev.backend.Entity.Speciality;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*" ,exposedHeaders="Access-Control-Allow-Origin" )
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
        return courseService.getCoursessByClassroom(classroomId);
    }

    @PutMapping("/enroll/{courseId}/{studentId}")
    public ResponseEntity<?> enrollCourse(@PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId) {
        try {
            courseService.courseEnroll(studentId, courseId);
            return ResponseEntity.ok().build();  // 200 OK
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());  // 500 Internal Server Error
        }
    }

    @GetMapping("/retrieve-courses-by-domain/{Speciality}")
    public List<Course> getAllCoursesBydomain(@PathVariable("Speciality") Speciality speciality) {
        return courseService.getCourseByCourseDomain(speciality);
    }

    @PutMapping("/course-Rating/{IdUser}/{IdCourse}/{Rating}")
    public Course AddRating(@PathVariable("IdUser") String IdUser , @PathVariable("IdCourse") String IdCourse,@PathVariable("Rating") Float Rating) {
       return   courseService.addRating(IdUser,IdCourse,Rating);
    }





}
