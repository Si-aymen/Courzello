package com.pidev.backend.Entity;
import jakarta.persistence.*;
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
    private String courseDomain;
    private Long courseDuration;
    private Date dateAdded;
    private float coursePrice ;
    private  String courseDescription  ;
    //upload course
    private String pdfUrl;
    private String videoUrl;

    @DBRef
    private Set<Chapter> chapters = new HashSet<>();

    @DBRef
    private Set<Classroom> classrooms = new HashSet<>();




}
