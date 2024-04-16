package com.pidev.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Classrooms")
public class Classroom {


    @MongoId
    private String id;
    private int classroomCapacity;
    private String classroomName;
    @Enumerated(EnumType.STRING)
<<<<<<< HEAD
    private ClassroomLvl classroomLvl ;
    @Enumerated(EnumType.STRING)
    private Speciality speciality ;

    private float classroomRating  ;// to be contunied

=======
    private  ClassroomLvl classroomLvl ;
    @Enumerated(EnumType.STRING)
    private Speciality  speciality ;

>>>>>>> 9c94761f2eb0aa9a853227c20cfce771558f1a98

    // association User Teachers
    @DBRef
    @JsonIgnore
    private Set<User> teachers =new HashSet<>();


    // association User Studnets
    @DBRef
    @JsonIgnore
    private Set<User> studnets  =new HashSet<>();

    // association User courses
    @DBRef
    @JsonIgnore
    private Set<Course> courses  =new HashSet<>();

}