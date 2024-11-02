package com.pidev.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Attachment")
@Builder
@ToString
public class Attachment {
    @Id
    @MongoId
    private String id;
    private String location;
    private AttachmentType type;

    @Transient
    private String data;

    @DBRef
    @JsonIgnore
    private Reclamation reclamation;

    public Attachment() {}
    public static Attachment Empty_() {
        return Attachment.builder().id("unknown").build();
    }
}
