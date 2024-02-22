package com.pidev.backend.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must only contain alphanumeric characters.")
    @MongoId
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private Role role;


    @ManyToOne
    private Department department;
}
