package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Conversation;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.ConversationRepository;
import com.pidev.backend.Service.ConversationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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

    @Override
    public Conversation modifyConversation(Conversation conversation) {
        return conversationRepository.save(conversation) ;
    }

    @Override
    public void deleteConversation(Long conversationId) {

        conversationRepository.deleteById(conversationId);
        System.out.println("siprimééééé");

    }


}
