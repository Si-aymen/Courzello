package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Attachment;
import com.pidev.backend.Entity.AttachmentType;
import com.pidev.backend.Repository.AttachRepository;
import com.pidev.backend.Service.AttachementService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachementService {
    @Autowired
    private AttachRepository attachment_repo;
    @Override
    public List<Attachment> getAllAttachements() {
        return attachment_repo.findAll();
    }

    @Override
    public Attachment getAttachementById(String id) {
        return attachment_repo.findById(id).orElseGet(Attachment::Empty_);
    }

    @Override
    public Attachment createAttachement(Attachment attachment) {
        AttachmentType t = AttachmentType.FILE;
        String location="";
        switch(attachment.getType()) {
            case IMAGE:
                String data=attachment.getData();
                String base64Image = data.split(",")[1];
                String extension=data.split("/")[1].split(";")[0];
                location=this.save_image(base64Image, extension);
                break;
        }

        attachment.setLocation(location);
        return attachment_repo.save(attachment);
    }

    @Override
    public Attachment updateAttachement(Attachment attachment) {
        return attachment_repo.save(attachment);
    }

    @Override
    public void deleteAttachement(String id) {
        attachment_repo.deleteById(id);
    }

    @Override
    public void deleteAllAttachements() {
        attachment_repo.deleteAll();
    }

    @Override
    public String base64_encode(String filePath) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    @Override
    public String save_image(String base64, String extension) {
        String image_name = UUID.randomUUID().toString();
        String location = "C:\\Users\\seifa.AFI\\pi\\Courzello\\Pidev\\Backend\\backendPidev\\src\\main\\resources\\images\\post\\"+image_name+"."+extension;
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        try {
            Files.write(Paths.get(location), decodedBytes);
            return location;
        }
        catch(IOException ex) {
            return "";
        }

    }
}
