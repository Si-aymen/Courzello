package com.pidev.backend.Service;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.User;

import java.util.List;

public interface ClassroomService {
    List<Classroom> GetAllClassrooms();
    Classroom GetClassroomByClassroomLevel(String lvl);
    Classroom AddClassroom(Classroom classroom);
//    User updateUser(String login, User user);
//    void deleteUser(String login);
}
