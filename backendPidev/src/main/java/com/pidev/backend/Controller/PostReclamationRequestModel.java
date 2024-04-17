package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Attachment;
import com.pidev.backend.Entity.Reclamation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostReclamationRequestModel {
    private Reclamation rec;
    private Attachment att;
}
