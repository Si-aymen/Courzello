package com.pidev.backend.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")

public class User {

    @Id
    private String id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    @Enumerated( EnumType.STRING )
    private Role role;

    // Association classroom
    @DBRef
    private Set<Classroom> classrooms = new HashSet<>();

}
