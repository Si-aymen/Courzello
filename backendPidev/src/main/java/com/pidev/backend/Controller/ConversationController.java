package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Conversation;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.ConversationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@AllArgsConstructor
@RequestMapping("/conversations")

public class ConversationController {

    ConversationService conversationService;

    @PostMapping("/add-conversation")
    public Conversation addConversation(@RequestBody Conversation c) {
        return conversationService.addConversation(c);
    }

    @GetMapping("/retrieve-conversations")
    public List<Conversation> getAllConversations() {
        return conversationService.getALLConversations();
    }

    @PutMapping ("/modify-conversation")
    public Conversation modifyConversation(@RequestBody Conversation c) {
        return conversationService.modifyConversation(c);
    }
    @DeleteMapping("/delete-conversation/{conversation-id}")
    public void deleteConversation(@PathVariable("conversation-id") String conversationId) {
        conversationService.deleteConversation(conversationId);
    }



}
