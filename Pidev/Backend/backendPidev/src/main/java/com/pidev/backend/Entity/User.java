package com.pidev.backend.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")

public class User {
    @Id
    @MongoId
    private String id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private Role role;

    // Getters and setters
}
