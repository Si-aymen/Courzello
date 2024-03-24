package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.Course;
import com.pidev.backend.Entity.Role;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.ClassroomRepository;
import com.pidev.backend.Repository.CourseRepository;
import com.pidev.backend.Repository.UserRepository;
import com.pidev.backend.Service.ClassroomService;
import com.pidev.backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    ClassroomRepository classroomRepository ;
    UserRepository userRepository ;
    CourseRepository courseRepository ;
    @Override
    public List<Classroom> GetAllClassrooms() {
        return classroomRepository.findAll() ;     }

    @Override
    public Classroom GetClassroomByClassroomLevel(String lvl) {
        return classroomRepository.getClassroomByClassroomName( lvl);
    }

    @Override
    public Classroom AddClassroom(Classroom classroom) {
        return classroomRepository.save(classroom) ;
    }

    @Override
    public Classroom UpdateClassroom(Classroom classroom) {
        return null;
    }

    @Override
    public void DeleteClassroom(String Name) {
        Classroom c = classroomRepository.getClassroomByClassroomName(Name);
        classroomRepository.delete(c);

    }



    @Override
    public void AddTeachersToClassroom(String IdClassroom, List<String> ListeTeacherLogin) {
        Classroom c = classroomRepository.getClassroomByClassroomName(IdClassroom);
        //Set<User> teachers = new HashSet<>();
        System.out.println(c.getId());

        for (String s : ListeTeacherLogin) {
            User u = userRepository.getUserByLoginAndRole(s, Role.TEACHER);
            System.out.println(u.getLogin());
            u.getClassrooms().add(c);
            userRepository.save(u);
            //teachers.add(u);
            c.getTeachers().add(u);
            classroomRepository.save(c);

        }
        //c.setTeachers(teachers);


    }

    @Override
    public void AddStudnetsToClassroom(String IdClassroom, List<String> ListeStudentsLogin) {
        Classroom c = classroomRepository.getClassroomByClassroomName(IdClassroom);
        System.out.println(c.getId());

        for (String s : ListeStudentsLogin) {
            User u = userRepository.getUserByLoginAndRole(s, Role.STUDENT);
            System.out.println(u.getLogin());
            u.getClassrooms().add(c);
            userRepository.save(u);
            c.getStudnets().add(u);
            classroomRepository.save(c);

        }

    }

    @Override
    public void AddCoursesToClassroom(String IdClassroom, List<String> ListeCourseesID) {
        Classroom c = classroomRepository.getClassroomByClassroomName(IdClassroom);
        System.out.println(c.getId());

        for (String s : ListeCourseesID) {
            Course course = courseRepository.findById(s).get();
            System.out.println(course.getCourseName());
            course.getClassrooms().add(c);
            courseRepository.save(course);
            c.getCourses().add(course);
            classroomRepository.save(c);

        }

    }

    @Override
    public Classroom GetById(String ClassroomId) {
        return classroomRepository.findById(ClassroomId).get();
    }

    @Override
    public void DeleteClassroomById(String ClassroomId) {
        classroomRepository.deleteById(ClassroomId);
    }

    @Override
    public void deleteTeacherFromClassroom(String teacherId, String classroomId) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);
        Optional<User> teacherOptional = userRepository.findById(teacherId);

        if (classroomOptional.isPresent() && teacherOptional.isPresent()) {
            Classroom classroom = classroomOptional.get();
            User teacher = teacherOptional.get();

            if (classroom.getTeachers().contains(teacher)) {
                classroom.getTeachers().remove(teacher);
                classroomRepository.save(classroom);
            }

            if (teacher.getClassrooms().contains(classroom)) {
                teacher.getClassrooms().remove(classroom);
                userRepository.save(teacher);
            }
        } else {
            // Handle the case where either the teacher or classroom doesn't exist
            // This could be logging an error, throwing a custom exception, etc.
        }
    }

    @Override
    public List<Classroom> GetClassroomsByTeacher(String IdTeacher) {
        User teacher = userRepository.findById(IdTeacher).get();
        return classroomRepository.getClassroomByTeachersContains(teacher);
    }

    @Override
    public List<Classroom> GetClassroomsByStudent(String IdStudent) {
        User student = userRepository.findById(IdStudent).get();
        return classroomRepository.getClassroomByStudnetsContains(student)  ;
    }

    @Override
    public void AddTeacherToClass(String IdTeacher, String IdClassroom) {

            Classroom c = classroomRepository.findById(IdClassroom).get();
            User u = userRepository.findById(IdTeacher).get();
            System.out.println(u.getLogin());
            u.getClassrooms().add(c);
            userRepository.save(u);
            c.getTeachers().add(u);
            classroomRepository.save(c);



    }

    @Override
    public void AddStudentToClass(String idStudent, String IdClassroom) {
        Classroom c = classroomRepository.findById(IdClassroom).get();
        User u = userRepository.findById(idStudent).get();
        System.out.println(u.getLogin());
        u.getClassrooms().add(c);
        userRepository.save(u);
        c.getStudnets().add(u);
        classroomRepository.save(c);
    }


}