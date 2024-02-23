package com.pidev.backend.Entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @MongoId
    private String id;
    private String login ;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Role role;

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
    // Getters and setters
}
