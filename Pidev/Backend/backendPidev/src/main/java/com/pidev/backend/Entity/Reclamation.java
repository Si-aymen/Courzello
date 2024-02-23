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
    private ReclamationType type;
    private ReclamtionState state;
    private ReclamationCategory category;
    @Transient // pour indiquer à JPA de ne pas persister ce champ
    private String automaticResponse;



    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    //private Attachement attachments;

    public String generateAutomaticResponse() {
        if ("INCIDENT".equals(this.type.name()) && "TECHNICAL".equals(this.category.name())) {
            return "Thank you for reporting the technical incident. Our team is working on resolving it.";
        } else if ("SUGGESTION".equals(this.type.name()) && "QUALITY".equals(this.category.name())) {
            return "We appreciate your suggestion for improving quality. It will be considered in our future enhancements.";
        } else if ("SUPPORT_REQUEST".equals(this.type.name()) && "ADMINISTRATIVE".equals(this.category.name())) {
            return "We apologize for any inconvenience caused. Your complaint regarding administrative has been noted, and we will address it promptly.";
        } else {
            // Retourner null si aucune réponse prédéfinie n'est trouvée pour les critères donnés
            return null;
        }
    }

    public Reclamation() {

    }


}
