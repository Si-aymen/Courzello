package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;



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
    private LocalDateTime creationDate;
    private LocalDateTime expectedResolutionDate;
    private int userImpact;
    private int priority;
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
    public Reclamation(LocalDateTime creationDate, LocalDateTime expectedResolutionDate, int userImpact) {
        this.creationDate = creationDate;
        this.expectedResolutionDate = expectedResolutionDate;
        this.userImpact = userImpact;
    }

    // Getters and setters

    public String getId() {
        return reclamationID;
    }

    public void setId(String id) {
        this.reclamationID = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpectedResolutionDate() {
        return expectedResolutionDate;
    }

    public void setExpectedResolutionDate(LocalDateTime expectedResolutionDate) {
        this.expectedResolutionDate = expectedResolutionDate;
    }

    public int getUserImpact() {
        return userImpact;
    }

    public void setUserImpact(int userImpact) {
        this.userImpact = userImpact;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


}
