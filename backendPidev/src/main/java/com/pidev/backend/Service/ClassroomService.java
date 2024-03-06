package com.pidev.backend.Service;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.Course;
import com.pidev.backend.Entity.User;

import java.util.List;

public interface ClassroomService {
    List<Classroom> GetAllClassrooms();
    Classroom GetClassroomByClassroomLevel(String lvl);
    Classroom AddClassroom(Classroom classroom);
    Classroom UpdateClassroom(Classroom classroom);
    void DeleteClassroom(String Name);

    //public Classroom AddNewTeachersToClassroom(Long IdClassroom , List<User> ListeTeacher) ;
    public void AddTeachersToClassroom(String IdClassroom , List<String> ListeTeacherLogin) ;
    public void AddStudnetsToClassroom(String IdClassroom , List<String> ListeStudentsLogin) ;

    //List<User> GetAllTeacherOfClassroom(String IdClassroom ) ;

    public void AddCoursesToClassroom (String IdClassroom , List<String> ListeCourseesID) ;

    //List<Course> GetAllCoursesOfClassroom(String ClassroomId);

    Classroom GetById(String ClassroomId) ;

    void DeleteClassroomById(String ClassroomId);





}
