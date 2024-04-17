package com.pidev.backend.Service;

import com.pidev.backend.Entity.Conversation;
import com.pidev.backend.Entity.User;

import java.util.List;

public interface ConversationService {
    Conversation addConversation (Conversation conversation);
    List<Conversation> getALLConversations();
    Conversation modifyConversation (Conversation conversation);
    public void deleteConversation(String ConversationId);

}
