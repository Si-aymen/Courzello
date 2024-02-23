package com.pidev.backend.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Reclamations")
@Builder
@ToString
public class Reclamation {
    @Id
    @MongoId
    private String reclamationID;
    private String dateSubmitted;
    private String subject;
    private String description;
    private ReclamationType type;


    //String juste bech testit bel postman, sinon tatbadel 3ala 7asb el attached files type
    @ToString.Exclude
    @DBRef
    @JoinColumn(name = "user_id")
    private User user;

//private String attachments;


    public Reclamation() {

    }

}
