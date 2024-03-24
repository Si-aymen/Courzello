package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Role;
import com.pidev.backend.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.UserService;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @GetMapping("/GetAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{login}")
    public User getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @GetMapping("/get-id/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserByLogin(id);
    }


    @PostMapping("/Save/user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{login}")
    public User updateUser(@PathVariable String login, @RequestBody User user) {
        return userService.updateUser(login, user);
    }

    @DeleteMapping("/{login}")
    public void deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
    }


    @PostMapping("signin/{login}/{pwd}")
    public boolean signinAdmin(@PathVariable String login, @PathVariable String pwd){
        User user=userRepository.findUserByLogin(login);
        return Objects.equals(user.getPassword(), pwd) && user.getRole()== Role.ADMIN;
    }


    @GetMapping("/classroom/{classroomId}")
    public List<User> getUsersByClassroom(@PathVariable String classroomId) {
        List<User> users = userService.getUsersByClassroom(classroomId);
        return  users ;
    }

    @GetMapping("/GetTeachers/{classroomId}")
    public List<User> getTeachersOfclassroom(@PathVariable String classroomId) {
        return userService.getTeachersOFClassroom(classroomId);
    }

    @GetMapping("/GetTeachers/")
    public List<User> getTeachers() {
        return userService.getTeachers();
    }

    @GetMapping("/GetStudents/{classroomId}")
    public List<User> getStudentsOfclassroom(@PathVariable String classroomId) {
        return userService.getStudentsOFClassroom(classroomId);
    }


}

