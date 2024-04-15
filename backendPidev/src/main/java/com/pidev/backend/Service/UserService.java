package com.pidev.backend.Service;

import com.pidev.backend.Entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByLogin(String login);
    User createUser(User user);
    User updateUser(String login, User user);
    void deleteUser(String login);
    public List<User> getUsersByClassroom(String classroomId) ;
    List<User> getTeachers();
    List<User> getTeachersOFClassroom(String IdClassroom) ;
    List<User> getStudentsOFClassroom(String IdClassroom) ;

    User getUserById(String id);

}
