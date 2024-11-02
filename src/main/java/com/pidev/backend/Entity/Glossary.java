package com.pidev.backend.Entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Glossary")
@Builder
@ToString
public class Glossary {
    @Id
    @MongoId
    private String glossaryId;
    private String term;
    private String definition;





}
