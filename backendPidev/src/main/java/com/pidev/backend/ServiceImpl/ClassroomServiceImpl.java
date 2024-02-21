package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Repository.ClassroomRepository;
import com.pidev.backend.Service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    ClassroomRepository classroomRepository ;
    @Override
    public List<Classroom> GetAllClassrooms() {
        return classroomRepository.findAll() ;     }

    @Override
    public Classroom GetClassroomByClassroomLevel(String lvl) {
        return null;
    }

    @Override
    public Classroom AddClassroom(Classroom classroom) {
        return classroomRepository.save(classroom) ;
    }
}
