package com.pidev.backend.Controller;


import com.pidev.backend.Entity.Attachment;
import com.pidev.backend.Service.AttachementService;
import com.pidev.backend.Service.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/attachements")
public class AttachementController {
    private AttachementService attachement_service;
    private ReclamationService reclamation_service;
    @GetMapping("/get_all")
    public List<Attachment> getAllAttachements() {
        return attachement_service.getAllAttachements();
    }
    @GetMapping("/get/{id}")
    public Attachment getAttachementById(@PathVariable String id) {
        return attachement_service.getAttachementById(id);
    }
    @PostMapping("/post")
    public Attachment createAttachement(@RequestBody Attachment attachment) {
        return attachement_service.createAttachement(attachment);
    }
    @PutMapping("/put")
    public Attachment updateAttachement(@RequestBody Attachment attachment) {
        return attachement_service.updateAttachement(attachment);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAttachement(@PathVariable String id) {
        attachement_service.deleteAttachement(id);
    }
    @DeleteMapping("/delete_all")
    public void deleteAllAttachement() {
        attachement_service.deleteAllAttachements();
    }

}
