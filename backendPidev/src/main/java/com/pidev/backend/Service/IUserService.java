package com.pidev.backend.Service;

import com.pidev.backend.Entity.User;

public interface IUserService {
    public User ajoutuser(User u);
    public User afficheuser(String idu);
}
