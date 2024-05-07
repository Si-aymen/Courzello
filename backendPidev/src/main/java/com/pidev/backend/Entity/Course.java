package com.pidev.backend.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    private int index ;
    private String courseName;
    private String courseLevel;
    private String url ;
    private String organization ;
    private String platform ;
    private Speciality courseDomain;
    private Long courseDuration;
    private Date dateAdded;
    private float coursePrice ;
    private  String courseDescription  ;
    //upload course
    private String pdfUrl;
    private String videoUrl;
    private List<Float> ratingList = new ArrayList<>();
    private float avRating ;
    @JsonDeserialize
    private String imgURL;
    @JsonDeserialize
    private String imagePath ;



    //Enrol
    @DBRef
    private Set<User> studentEnroling = new HashSet<>();

    //Chapters of course
    @DBRef
    private Set<Chapter> chapters = new HashSet<>();

    //classroom of the course
    @DBRef
    private Set<Classroom> classrooms = new HashSet<>();

    //User rate the course
    @DBRef
    private Set<User> UsersRated = new HashSet<>();




}
