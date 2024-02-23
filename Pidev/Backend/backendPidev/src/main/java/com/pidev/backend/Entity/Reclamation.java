package com.pidev.backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private ReclamationType type;


    //String juste bech testit bel postman, sinon tatbadel 3ala 7asb el attached files type
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

//private String attachments;


    public Reclamation() {

    }

}
