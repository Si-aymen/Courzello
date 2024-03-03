package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Classroom;
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
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String login, User user) {
        if (userRepository.existsById(login)) {
            user.setLogin(login);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(String login) {
        userRepository.deleteById(login);
    }

    public List<User> getUsersByClassroom(String classroomId) {
        List<User> users = userRepository.findAll();
        List<User> usersInClassroom = new ArrayList<>();
        for (User user : users) {
            for (Classroom classroom : user.getClassrooms()) {
                if (classroom.getId().equals(classroomId)) {
                    usersInClassroom.add(user);
                    break; // No need to continue checking other classrooms for this user
                }
            }
        }
        return usersInClassroom;
    }


}
