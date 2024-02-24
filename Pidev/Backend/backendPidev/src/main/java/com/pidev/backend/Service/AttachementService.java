package com.pidev.backend.Service;

import com.pidev.backend.Entity.Attachment;
import com.pidev.backend.Entity.AttachmentType;

import java.io.IOException;
import java.util.List;

public interface AttachementService {
        List<Attachment> getAllAttachements();
        Attachment getAttachementById(String reclamationID);
        Attachment createAttachement(Attachment reclamation);
        Attachment updateAttachement(Attachment reclamation);
        void deleteAttachement(String id);
        void deleteAllAttachements();

        public String base64_encode(String imagePath) throws IOException;
        public String save_image(String base64, String extension);
}
