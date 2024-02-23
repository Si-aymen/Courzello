package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentID;
    private String name;
    private String contactInformation;

}
