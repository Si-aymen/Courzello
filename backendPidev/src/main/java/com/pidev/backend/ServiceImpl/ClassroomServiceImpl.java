package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.Role;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.ClassroomRepository;
import com.pidev.backend.Repository.UserRepository;
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
    UserRepository userRepository ;
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



//    @Override
//    public void AddTeachersToClassroom(Long IdClassroom, List<String> ListeTeacherLogin) {
//        Classroom c = classroomRepository.getClassroomByIdClassroom(IdClassroom);
//        Set<User> teachers = new HashSet<>();
//
//        for (String s : ListeTeacherLogin) {
//            User u = userRepository.getUserByLoginAndRole(s, Role.TEACHER);
////            if (u.getClassrooms() == null){
////
////                Set<Classroom> classroomSet = new HashSet<>();
////                classroomSet.add(c);
////                u.setClassrooms(classroomSet);
////                userRepository.save(u);
////            }
////            else {
//                u.getClassrooms().add(c);
//
//           // }
//
//            teachers.add(u);
//            if(c.getTeachers() == null) {
//                c.setTeachers(teachers);
//            }
//            else {
//                c.getTeachers().add(u);
//            }
//        }
//
//       classroomRepository.save(c);
//    }
//

    @Override
    public void AddTeachersToClassroom(String IdClassroom, List<String> ListeTeacherLogin) {
        Classroom c = classroomRepository.getClassroomByClassroomLevel(IdClassroom);
        //Set<User> teachers = new HashSet<>();
        System.out.println(c.getId());

        for (String s : ListeTeacherLogin) {
            User u = userRepository.getUserByLoginAndRole(s, Role.TEACHER);
            System.out.println(u.getLogin());
            u.getClassrooms().add(c);
            userRepository.save(u);
            //teachers.add(u);
            c.getTeachers().add(u);
            classroomRepository.save(c);

        }
        //c.setTeachers(teachers);


    }

    @Override
    public void AddStudnetsToClassroom(String IdClassroom, List<String> ListeStudentsLogin) {
        Classroom c = classroomRepository.getClassroomByClassroomLevel(IdClassroom);
        System.out.println(c.getId());

        for (String s : ListeStudentsLogin) {
            User u = userRepository.getUserByLoginAndRole(s, Role.STUDENT);
            System.out.println(u.getLogin());
            u.getClassrooms().add(c);
            userRepository.save(u);
            c.getStudnets().add(u);
            classroomRepository.save(c);

        }

    }


}
