package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.Role;
import com.pidev.backend.Repository.ClassroomRepository;
import com.pidev.backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ClassroomRepository classroomRepository ;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByLogin(String login) {
        Optional<User> optionalUser = userRepository.findById(login);
        return optionalUser.orElse(null);
    }

    @Override
    public User createUser(User user) {
        if(userRepository.findUserByEmail(user.getEmail())!=null||userRepository.findUserByLogin(user.getLogin())!=null){
            throw new IllegalArgumentException("Login or email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String login, User user) {
        User userToUpdate = userRepository.findUserByLogin(login);
        if (userToUpdate != null) {
            user.setLogin(userToUpdate.getLogin());
            user.setId(userToUpdate.getId());

            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(String login) {
        userRepository.deleteUserByLogin(login);
    }



    @Override
    public List<User> getUsersByClassroom(String classroomId) {
        List<User> users = userRepository.findAll();
        List<User> usersInClassroom = new ArrayList<>();
        for (User user : users) {
            for (Classroom classroom : user.getClassrooms()) {
                if (classroom.getId().equals(classroomId)) {
                    usersInClassroom.add(user);
                    break;
                }
            }
        }
        return usersInClassroom;
    }

    @Override
    public List<User> getTeachers() {
        return userRepository.getUsersByRole(Role.TEACHER);
    }

    @Override
    public List<User> getTeachersOFClassroom(String IdClassroom) {
        Classroom classroom = classroomRepository.findById(IdClassroom).get();
        return userRepository.getUserByClassroomsAndRole(classroom , Role.TEACHER) ;
    }

    @Override
    public List<User> getStudentsOFClassroom(String IdClassroom) {
        Classroom classroom = classroomRepository.findById(IdClassroom).get();
        return userRepository.getUserByClassroomsAndRole(classroom , Role.STUDENT) ;
    }


}
