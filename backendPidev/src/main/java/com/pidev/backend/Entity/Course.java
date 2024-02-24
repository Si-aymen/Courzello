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
    private String id;
    private String courseName;
    private String courseLevel;
    private Long courseDuration;
    private Date dateAdded;
    @DBRef
    private Set<Chapter> chapters = new HashSet<>();


}
