package com.pidev.backend.Service;

import com.pidev.backend.Entity.Question;
import com.pidev.backend.Entity.Reponse;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Entity.Vote;
import com.pidev.backend.Iservice.IReponseService;
import com.pidev.backend.Repository.QuestionRepository;
import com.pidev.backend.Repository.ReponseRepository;
import com.pidev.backend.Repository.UserRepository;
import com.pidev.backend.Repository.VoteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReponseServiceImpl implements IReponseService {
    @Autowired
    ReponseRepository reponserepo;
    @Autowired
    UserRepository userrepo;
    @Autowired
    QuestionRepository questionrepo;
    @Autowired
    QuestionServiceImpl questserv;

    @Override
    public Reponse ajoutReponse(Reponse q,String idq ,String iduse) {

        Question qu =questionrepo.findById(idq).orElse(null);
        User u=userrepo.findById(iduse).orElse(null);
        if(qu!=null&&u!=null){
            String contenu = questserv.hashbadword(q.getContenue(),qu.getId(),u.getId()) ;
            q.setContenue(contenu);
            q.setQuestion(qu);
            q.setUser(u);
            reponserepo.save(q);
            if(qu.getReponses()!=null){
                qu.getReponses().add(q);
                questionrepo.save(qu);
            }else{
                List<Reponse> responses =new ArrayList<Reponse>();
                responses.add(q);
                qu.setReponses(responses);
                questionrepo.save(qu);
            }
            if(u.getReponses()!=null){
                u.getReponses().add(q);
                userrepo.save(u);
            }else{
                List<Reponse> responses =new ArrayList<Reponse>();
                responses.add(q);
                u.setReponses(responses);
                userrepo.save(u);;
            }
        }
        return reponserepo.save(q);
    }

    @Override
    public Reponse updateReponse(String idq, Reponse q) {
        Reponse qu =reponserepo.findById(idq).orElse(null);
        if (qu != null) {
            qu.setContenue(q.getContenue());
        }
        if (qu != null) {
            return reponserepo.save(qu);
        }
        return qu;
    }

    @Override
    public void deleteReponse(String idq) {
        Reponse e = reponserepo.findById(idq).orElse(null);
        if (e != null && e.getUser() != null && e.getQuestion() != null) {
            User u=e.getUser();
            Question q=e.getQuestion();
            for(Reponse vo :u.getReponses()){
                if(vo.equals(e)){
                    u.getReponses().remove(vo);
                    userrepo.save(u);
                    break;
                }
            }


            for(Reponse vo :q.getReponses()){
                if(vo.equals(e)){
                    q.getReponses().remove(vo);
                    System.out.println(q.getVotes().size());
                    questionrepo.save(q);
                    break;
                }
            }
        }
        reponserepo.deleteById(idq);
    }

    @Override
    public Reponse affichDetailReponse(String idq) {
        return reponserepo.findById(idq).orElse(null);
    }

    @Override
    public List<Reponse> afficherReponse() {
        return reponserepo.findAll();
    }

    @Override
    public List<Reponse> afficherReponsebypost(String idq) {
        Question q = questionrepo.findById(idq).orElse(null);
        if (q != null) {
            return q.getReponses();
        }
        return null;
    }
}
