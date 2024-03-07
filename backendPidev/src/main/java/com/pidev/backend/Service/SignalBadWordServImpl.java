package com.pidev.backend.Service;

import com.pidev.backend.Entity.Question;
import com.pidev.backend.Entity.Reponse;
import com.pidev.backend.Entity.SignalBadword;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Iservice.ISignalBadWordService;
import com.pidev.backend.Repository.QuestionRepository;
import com.pidev.backend.Repository.ReponseRepository;
import com.pidev.backend.Repository.SignaBAdWordRepository;
import com.pidev.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignalBadWordServImpl implements ISignalBadWordService {
    @Autowired
    SignaBAdWordRepository sbwrepo;
    @Autowired
    QuestionServiceImpl questrepo;
    @Autowired
    UserSErviceImpl userrpo;
    @Autowired
    ReponseServiceImpl reponserepo;



    @Override
    public List<SignalBadword> afficheBadword() {
        return sbwrepo.findAll();
    }
}
