package com.pidev.backend.Service;

import com.pidev.backend.Entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    Course addCourse (Course course);
    List<Course> getALLCourses();
    Course modifyCourse (Course course);
    public void deleteCourse(String CourseId);

    public void uploadPdf(MultipartFile file, String courseId) ;
}
