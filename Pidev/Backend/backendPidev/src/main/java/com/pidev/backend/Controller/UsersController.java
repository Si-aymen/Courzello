package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Users;
import com.pidev.backend.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    @GetMapping("/getall")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getone/{login}")
    public Users getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping("/post")
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PutMapping("/update/{login}")
    public Users updateUser(@PathVariable String login, @RequestBody Users user) {
        return userService.updateUser(login, user);
    }

    @DeleteMapping("/delete/{login}")
    public void deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
    }
}
