package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.ClassroomRepository;
import com.pidev.backend.Service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    ClassroomRepository classroomRepository ;
    @Override
    public List<Classroom> GetAllClassrooms() {
        return classroomRepository.findAll() ;     }

    @Override
    public Classroom GetClassroomByClassroomLevel(String lvl) {
        return classroomRepository.getClassroomByClassroomLevel( lvl);
    }

    @Override
    public Classroom AddClassroom(Classroom classroom) {
        return classroomRepository.save(classroom) ;
    }

    @Override
    public Classroom UpdateClassroom(Classroom classroom) {
        return null;
    }

    @Override
    public void DeleteClassroom(Long id) {
        classroomRepository.deleteById(id);

    }

    @Override
    public Classroom AddNewTeachersToClassroom(Long IdClassroom , List<User> ListeTeacher) {

        Classroom classroom = classroomRepository.findById(IdClassroom).get() ;
        Set<User> teachers = new HashSet<>(ListeTeacher) ;
        classroom.setTeachers(teachers);
        return classroom ;
    }




}
