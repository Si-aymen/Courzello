package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.SignalBadword;
import com.pidev.backend.Service.ISignalBadWordService;
import com.pidev.backend.Repository.SignaBAdWordRepository;
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
    UserServiceImpl userrpo;
    @Autowired
    ReponseServiceImpl reponserepo;



    @Override
    public List<SignalBadword> afficheBadword() {
        return sbwrepo.findAll();
    }
}
