package com.pidev.backend.Iservice;

import com.pidev.backend.Entity.User;

public interface IUserService {
    public User ajoutuser(User u);
    public User afficheuser(String idu);
}
