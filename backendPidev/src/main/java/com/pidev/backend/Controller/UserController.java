package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Reponse;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.UserSErviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/user")
public class USerController {
    @Autowired
    UserSErviceImpl userserv;
    @PostMapping("/add-User")
    @ResponseBody
    public User ajoutReponse(@RequestBody User p) {
        return userserv.ajoutuser(p);

    }
}
