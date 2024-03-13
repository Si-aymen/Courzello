package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Classroom;
import com.pidev.backend.Entity.Role;

import com.pidev.backend.Entity.Speciality;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.pidev.backend.Entity.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User getUserByLoginAndRole(String login , Role role ) ;
    User deleteUserByLogin(String login);
    User findUserByLogin(String login);
    User findUserByEmail(String email);

    List<User> getUserBySpeciality(Speciality speciality) ;

    List<User> getUserByRole(Role role ) ;


   // List<User> findAllByClassrooms(String Classrooms) ;
}
