package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Conversation;
import com.pidev.backend.Repository.ConversationRepository;
import com.pidev.backend.Service.ConversationService;

import java.util.List;

public class ConversationServiceImpl implements ConversationService {

    ConversationRepository conversationRepository;


    @Override
    public Conversation addConversation(Conversation conversation) {

            return conversationRepository.save(conversation);
    }

    @Override
    public List<Conversation> getALLConversations() {
        return conversationRepository.findAll();
    }


}
