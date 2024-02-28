package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@AllArgsConstructor
@RequestMapping("/classrooms")
public class ClassroomController {

    private ClassroomService classroomService ;

    @GetMapping
    public List<Classroom> getAllClassroom() {
        return classroomService.GetAllClassrooms();
    }

    @DeleteMapping("/DeleteClassroom/{ClassName}")
    public void DeleteClassroom(@PathVariable("ClassName") String ClassName) {
         classroomService.DeleteClassroom(ClassName);
    }


    @GetMapping("/{lvl}")
    public Classroom getClassroomByLvl(@PathVariable("lvl") String lvl) {
        return classroomService.GetClassroomByClassroomLevel(lvl);
    }

    @PostMapping("/Save")
    public Classroom AddClassRoom(@RequestBody Classroom classroom) {
        return classroomService.AddClassroom(classroom);
    }

    @PutMapping("/AddTeachers/{IDClassroom}")
    public Classroom AddNewTeachersToClassroom(@PathVariable("IDClassroom") Long id,@RequestBody List<User> teachers) {
        return classroomService.AddNewTeachersToClassroom(id,teachers);
    }
    @PutMapping("/AddTeachers/{IDClassroom}/{Logins}")
    public void AddTeachersToClassroom(@PathVariable("IDClassroom") String id,@PathVariable("Logins") List<String> logins) {
       classroomService.AddTeachersToClassroom(id, logins);
    }
    @PutMapping("/AddStudnets/{IDClassroom}/{Logins}")
    public void AddStudentsToClassroom(@PathVariable("IDClassroom") String id,@PathVariable("Logins") List<String> logins) {
       classroomService.AddStudnetsToClassroom(id, logins);
    }


}