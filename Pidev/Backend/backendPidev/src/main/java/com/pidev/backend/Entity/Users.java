package com.pidev.backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Document("testing_users")
public class Users {
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
