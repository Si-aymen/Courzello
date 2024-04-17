package com.pidev.backend.Controller;

import com.pidev.backend.Entity.SignalBadword;
import com.pidev.backend.ServiceImpl.SignalBadWordServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/badword")
public class BadWordController {
    @Autowired
    SignalBadWordServImpl sbwserv;
    @GetMapping("/get-badword")
    @ResponseBody
    public List<SignalBadword> getBAdword() {
        return sbwserv.afficheBadword();

    }
}
