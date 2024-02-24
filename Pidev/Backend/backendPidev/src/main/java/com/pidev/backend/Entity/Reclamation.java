package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

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
    private Date dateSubmitted;
    private String subject;
    private String description;
    private ReclamtionState state;
    private ReclamationCategory category;

    @DBRef
    private Attachment attachment;

    @DBRef
    private User user;


    //private Attachement attachments;

    public Reclamation() {

    }


}
