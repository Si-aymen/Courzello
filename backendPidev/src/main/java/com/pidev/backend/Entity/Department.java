package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Departments")
public class Department {

    @Getter
    @MongoId
    private String departmentId;

    private String name;
    private String contactInformation;
    @DBRef
    private List<User> teachers;

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }


}
