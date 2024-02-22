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
@Document(collection = "Chapters")

public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idChapter;
    private String chapterName;
    private String duration;

}
