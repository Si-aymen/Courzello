package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.Course;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.CourseRepository;
import com.pidev.backend.Repository.UserRepository;
import com.pidev.backend.Service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException; // Added import
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private  final UserRepository userRepository ;

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
    public void deleteCourse(String courseId) {
        Course c = courseRepository.findById(courseId).orElse(null); // Handling if course is not found
        if (c != null) {
            courseRepository.delete(c);
        } else {
            throw new RuntimeException("Course not found with id: " + courseId);
        }
    }

    @Override
    public void uploadPdf(MultipartFile file, String courseId) {
        try {
            // Get the byte array from the uploaded file
            byte[] bytes = file.getBytes();

            // Define the directory to save PDF files
            String directory = "C:/Users/manou/Desktop/pidev All/CoursePDF"; // Update this with your directory path

            // Create the directory if it doesn't exist
            Path path = Paths.get(directory);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Save the file with courseId as filename
            String fileName = courseId + ".pdf";
            Path filePath = Paths.get(directory, fileName);
            Files.write(filePath, bytes);

            // Get the course from the repository
            Course course = courseRepository.findById(courseId).orElse(null);
            if (course != null) {
                // Set the pdfUrl field with the URL of the uploaded PDF file
                String pdfUrl = "/pdfs/" + fileName; // Assuming "/pdfs" is the endpoint to access PDF files
                course.setPdfUrl(pdfUrl);

                // Save the course entity with the updated pdfUrl
                courseRepository.save(course);
            } else {
                throw new RuntimeException("Course not found with id: " + courseId);
            }
        } catch (IOException e) {
            // Handle the IOException here
            throw new RuntimeException("Failed to store the file", e);
        }
    }

    @Override
    public List<Course> getCoursessByClassroom(String classroomId) {

        List<Course> Courses = courseRepository.findAll();
        List<Course> CoursesInClassroom = new ArrayList<>();
        for (Course Course : Courses) {
            for (Classroom classroom : Course.getClassrooms()) {
                if (classroom.getId().equals(classroomId)) {
                    CoursesInClassroom.add(Course);
                    break;
                }
            }
        }
        return CoursesInClassroom;


    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId).get() ;
    }

    @Override
    @Transactional
    public void courseEnroll(String idStudent, String idCourse) {
        Optional<User> userOptional = userRepository.findById(idStudent);
        Optional<Course> courseOptional = courseRepository.findById(idCourse);

        if (userOptional.isPresent() && courseOptional.isPresent()) {
            User u = userOptional.get();
            Course c = courseOptional.get();

            c.getStudentEnroling().add(u);
            u.getCoursesEnrolled().add(c) ;
            userRepository.save(u);
            courseRepository.save(c) ;
        } else {
            throw new EntityNotFoundException("User or Course not found");
        }


    }


}
