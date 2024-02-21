package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.ClassroomService;
import com.pidev.backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/Classroom")
public class ClassroomController {

    private ClassroomService classroomService ;

    @GetMapping
    public List<Classroom> getAllClassroom() {
        return classroomService.GetAllClassrooms();
    }

    @PostMapping
    public Classroom AddClassRoom(@RequestBody Classroom classroom) {
        return classroomService.AddClassroom(classroom);
    }


}
