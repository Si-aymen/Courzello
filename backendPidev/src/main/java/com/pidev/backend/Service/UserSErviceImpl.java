//package com.pidev.backend.Service;
//
//import com.pidev.backend.Entity.User;
//import com.pidev.backend.Iservice.IUserService;
//import com.pidev.backend.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserSErviceImpl implements IUserService {
//    @Autowired
//    UserRepository userrepo;
//    @Override
//    public User ajoutuser(User u) {
//        return userrepo.save(u);
//    }
//
//    @Override
//    public User afficheuser(String idu) {
//        return userrepo.findById(idu).orElse(null);
//    }
//}
