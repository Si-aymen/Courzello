package com.pidev.backend.Service;

import com.pidev.backend.Entity.Users;

import java.util.List;

public interface UsersService {
    List<Users> getAllUsers();
    Users getUserByLogin(String login);
    Users createUser(Users user);
    Users updateUser(String login, Users user);
    void deleteUser(String login);
}
