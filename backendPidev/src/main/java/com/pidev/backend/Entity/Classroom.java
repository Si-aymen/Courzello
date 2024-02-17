package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idClassroom;
    private int classroomCapacity;
    //String / int (?)
    private String classroomLevel;

    // association User Teachers
    @ManyToMany(mappedBy="classrooms", cascade = CascadeType.ALL)
    private Set<User> teachers ;


    // association User Studnets
    @ManyToMany(mappedBy="classrooms", cascade = CascadeType.ALL)
    private Set<User> studnets  ;

}
