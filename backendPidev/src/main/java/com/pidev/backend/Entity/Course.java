package com.pidev.backend.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Courses")
public class Course {

    @MongoId
    // details course
    private String id;
    private String courseName;
    private String courseLevel;
    private Speciality courseDomain;
    private Long courseDuration;
    private Date dateAdded;
    private float coursePrice ;
    private  String courseDescription  ;
    //upload course
    private String pdfUrl;
    private String videoUrl;

    //Enrol
    @DBRef
    private Set<User> studentEnroling = new HashSet<>();

    @DBRef
    private Set<Chapter> chapters = new HashSet<>();

    @DBRef
    private Set<Classroom> classrooms = new HashSet<>();




}
