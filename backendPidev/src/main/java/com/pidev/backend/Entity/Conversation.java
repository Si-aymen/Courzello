package com.pidev.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ConversationId ;
    String Message ;
    String Description ;
    @Enumerated(EnumType.STRING)
    Theme theme ;


}
