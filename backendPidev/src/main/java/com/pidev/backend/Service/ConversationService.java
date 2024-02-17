package com.pidev.backend.Service;

import com.pidev.backend.Entity.Conversation;

import java.util.List;

public interface ConversationService {
    Conversation addConversation (Conversation conversation);
    List<Conversation> getALLConversations();
}
