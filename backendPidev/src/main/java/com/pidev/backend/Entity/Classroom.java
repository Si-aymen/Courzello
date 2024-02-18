package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Classrooms")
public class Classroom {


    @Id
    private String id;
    private int classroomCapacity;
    private String classroomLevel;

    // association User Teachers
    @DBRef
    private Set<User> teachers =new HashSet<>();


    // association User Studnets
    @DBRef
    private Set<User> studnets  =new HashSet<>();

}
