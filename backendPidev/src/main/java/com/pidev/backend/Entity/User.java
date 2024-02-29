package com.pidev.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    @Enumerated( EnumType.STRING )
    private Role role;
    @DBRef
    private List<Vote> votes=new ArrayList<Vote>();;
    @DBRef
    @JsonIgnore
    private List<Question> questions;
    @DBRef
    @JsonIgnore
    private List<Reponse> reponses;

    // Association classroom
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Classroom> classrooms;

    // Getters and setters
}
