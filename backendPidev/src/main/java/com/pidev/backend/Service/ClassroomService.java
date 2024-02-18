package com.pidev.backend.Service;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.User;

import java.util.List;

public interface ClassroomService {
    List<Classroom> GetAllClassrooms();
    Classroom GetClassroomByClassroomLevel(String lvl);
    Classroom AddClassroom(Classroom classroom);
    Classroom UpdateClassroom(Classroom classroom);
    void DeleteClassroom(Long id);

    public Classroom AddNewTeachersToClassroom(Long IdClassroom , List<User> ListeTeacher) ;
    public void AddTeachersToClassroom(String IdClassroom , List<String> ListeTeacherLogin) ;


}
