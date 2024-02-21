package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Entity
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


    //String juste bech testit bel postman, sinon tatbadel 3ala 7asb el attached files type
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

//private String attachments;


    public Reclamation() {

    }

}
