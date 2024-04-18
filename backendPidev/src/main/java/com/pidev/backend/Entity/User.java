package com.pidev.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")

public class User {

    public static User CONNECTEDUSER;
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

    private String id;
    @Indexed(unique = true)
    @Id
    private String login ;

    private String password;
    @MongoId

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

    //teachers if user.role = teacher
    @DBRef
    private Set<User> Teachers = new HashSet<>();


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

    @DBRef
    @JsonIgnore
    private List<User> followers = new ArrayList<>();

    @DBRef
    @JsonIgnore
    private List<User> following = new ArrayList<>();




    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}