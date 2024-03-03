package com.pidev.backend.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.UserService;
import java.util.List;

@RestController
@AllArgsConstructor

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping("/GetAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{login}")
    public User getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
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


    @GetMapping("/classroom/{classroomId}")
    public List<User> getUsersByClassroom(@PathVariable String classroomId) {
        List<User> users = userService.getUsersByClassroom(classroomId);
        return  users ;
    }


}

