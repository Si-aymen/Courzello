package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Conversation;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Service.ConversationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
public class ConversationController {
    ConversationService conversationService;
    @PostMapping
    public Conversation addConversation (@RequestBody Conversation c) {
        return conversationService.addConversation(c);
    }

    @GetMapping
    public List<Conversation> getAllConversations() {
        return conversationService.getALLConversations();
    }
}
