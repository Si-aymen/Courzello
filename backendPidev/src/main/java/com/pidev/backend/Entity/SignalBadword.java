package com.pidev.backend.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "SignalBadWord")
public class SignalBadword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private  String badword ;
    @DBRef
    private Question question;
    @DBRef
    private User user;
}
