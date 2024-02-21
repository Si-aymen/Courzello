package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Repository.UsersRepository;
import com.pidev.backend.Service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.pidev.backend.Entity.Users;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UsersRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserByLogin(String login) {
        Optional<Users> optionalUsers = userRepository.findById(login);
        return optionalUsers.orElse(null);
    }

    @Override
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(String login, Users user) {
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
}
