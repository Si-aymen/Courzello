package com.pidev.backend.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")

public class User {
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role=" + role +
                ", speciality=" + speciality +
                '}';
    }

    @MongoId
    private String id;
    @Indexed(unique = true)
    @Id
    private String login ;
    private String password;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Speciality speciality =null;

    //Courses if user = student
    @DBRef
    private Set<Course> CoursesEnrolled = new HashSet<>() ;


    // Association classroom
    @DBRef
    private Set<Classroom> classrooms = new HashSet<>();
    @DBRef
    private List<Vote> votes = new ArrayList<Vote>();
    @DBRef
    @JsonIgnore
    private List<Question> questions;
    @DBRef
    @JsonIgnore
    private List<Reponse> reponses;

    @DBRef
    private List<Conversation> conversations = new ArrayList<>();
}
