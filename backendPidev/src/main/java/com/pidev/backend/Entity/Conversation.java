package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "conversations")
public class Conversation {
    @MongoId
    String id ;
    String message ;
    String description ;
    @Enumerated(EnumType.STRING)
    Theme theme ;


}
