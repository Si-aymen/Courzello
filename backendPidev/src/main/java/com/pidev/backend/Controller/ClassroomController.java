package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@AllArgsConstructor
@RequestMapping("/classrooms")
public class ClassroomController {

    private ClassroomService classroomService ;

    @GetMapping
    public List<Classroom> getAllClassroom() {
        return classroomService.GetAllClassrooms();
    }
    @GetMapping("/GetClassroom/{ClassroomId}")
    public Classroom getClassroomById(@PathVariable("ClassroomId")String classroomid ) {
        return classroomService.GetById(classroomid);
    }

    @DeleteMapping("/DeleteClassroom/{ClassName}")
    public void DeleteClassroom(@PathVariable("ClassName") String ClassName) {
         classroomService.DeleteClassroom(ClassName);
    }

    @DeleteMapping("/DeleteClassroomById/{ClassroomId}")
    public void DeleteClassroomById(@PathVariable("ClassroomId") String classId) {
         classroomService.DeleteClassroomById(classId);
    }


    @GetMapping("/{lvl}")
    public Classroom getClassroomByLvl(@PathVariable("lvl") String lvl) {
        return classroomService.GetClassroomByClassroomLevel(lvl);
    }

    @PostMapping("/Save")
    public Classroom AddClassRoom(@RequestBody Classroom classroom) {
        return classroomService.AddClassroom(classroom);
    }

//    @PutMapping("/AddTeachers/{IDClassroom}")
//    public Classroom AddNewTeachersToClassroom(@PathVariable("IDClassroom") Long id,@RequestBody List<User> teachers) {
//        return classroomService.AddNewTeachersToClassroom(id,teachers);
//    }
    @PutMapping("/AddTeachers/{IDClassroom}/{Logins}")
    public void AddTeachersToClassroom(@PathVariable("IDClassroom") String id,@PathVariable("Logins") List<String> logins) {
       classroomService.AddTeachersToClassroom(id, logins);
    }
    @PutMapping("/AddStudnets/{IDClassroom}/{Logins}")
    public void AddStudentsToClassroom(@PathVariable("IDClassroom") String id,@PathVariable("Logins") List<String> logins) {
       classroomService.AddStudnetsToClassroom(id, logins);
    }


    @PutMapping("/AddCourses/{IDClassroom}/{coursesID}")
    public void addCoursesToClassroom(@PathVariable("IDClassroom") String id,@PathVariable("coursesID") List<String> coursesID) {
        classroomService.AddCoursesToClassroom(id, coursesID);
    }

    @PutMapping("/DeleteTeacher/{IDClassroom}/{IDTeacher}")
    public void DeleteTeacherFromClassroom(@PathVariable("IDClassroom") String idClass,@PathVariable("IDTeacher") String IdTeacher) {
        classroomService.deleteTeacherFromClassroom(IdTeacher,idClass);
    }




}
