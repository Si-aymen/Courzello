package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.*;
import com.pidev.backend.Repository.HashtagRepository;
import com.pidev.backend.Repository.QuestionRepository;
import com.pidev.backend.Repository.SignaBAdWordRepository;
import com.pidev.backend.Repository.UserRepository;
import com.pidev.backend.Service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    QuestionRepository questionrepo;
    @Autowired
    UserRepository userepo;

    @Autowired
    SignaBAdWordRepository sbwserv;
    @Autowired
    HAshtagServiceImpl hashserv;
    @Autowired
    HashtagRepository hashrepo;

    @Override
    public Question ajoutQuestion(Question q,String idu) {
        User u =userepo.findById(idu).orElse(null);
        if (u != null && u.getQuestions() != null) {
            u.getQuestions().add(q);
        }

        if (u != null) {
            userepo.save(u);
        }
        q.setUser(u);
        questionrepo.save(q);
        q.setContenue(this.hashbadword(q.getContenue(),q.getId(),idu));

        for(Technologie t:q.getTech()){
            Hashtag hashtag =new Hashtag();
            hashtag.setTechnologie(t);
            boolean  etat=hashserv.ajouthashtag(hashtag);
            if(etat){ //Si le hashtag a été ajouté avec succès, l'ajoute à la question
                if(q.getHashtag()!=null){
                    q.getHashtag().add(hashtag);
                }else{
                    List<Hashtag> hashtags =new ArrayList<Hashtag>();
                    hashtags.add(hashtag);
                    q.setHashtag(hashtags);
                }
            }else{// Si le hashtag existe déjà, le récupère et l'ajoute à la question
                for(Hashtag h:hashrepo.findAll()){
                    if(h.getTechnologie().equals(t)) {
                        if(q.getHashtag()!=null){
                            q.getHashtag().add(h);
                        }else{
                            List<Hashtag> hashtags =new ArrayList<Hashtag>();
                            hashtags.add(h);
                            q.setHashtag(hashtags);
                        }
                    }
                }
            }

        }

        return questionrepo.save(q);
    }

    public Question updateQuestion(String idq, Question q) {
        Question qu = questionrepo.findById(idq).orElse(null);
        if (qu != null) {
            qu.setContenue(this.hashbadword(q.getContenue(), qu.getId(), qu.getUser().getId()));

            // Mise à jour des technologies si elles sont fournies
            if (q.getTech() != null && !q.getTech().isEmpty()) {
                qu.setTech(q.getTech());
                qu.setHashtag(null); // Supprimer les hashtags existants car les technologies sont mises à jour
                for (Technologie t : q.getTech()) {
                    Hashtag hashtag = new Hashtag();
                    hashtag.setTechnologie(t);
                    boolean etat = hashserv.ajouthashtag(hashtag);
                    if (etat) {
                        if (qu.getHashtag() != null) {
                            qu.getHashtag().add(hashtag);
                        } else {
                            List<Hashtag> hashtags = new ArrayList<>();
                            hashtags.add(hashtag);
                            qu.setHashtag(hashtags);
                        }
                    } else {
                        for (Hashtag h : hashrepo.findAll()) {
                            if (h.getTechnologie().equals(t)) {
                                if (qu.getHashtag() != null) {
                                    qu.getHashtag().add(h);
                                } else {
                                    List<Hashtag> hashtags = new ArrayList<>();
                                    hashtags.add(h);
                                    qu.setHashtag(hashtags);
                                }
                            }
                        }
                    }
                }
            }

            // Sauvegarde de la question une seule fois après avoir terminé toutes les opérations de mise à jour
            return questionrepo.save(qu);
        }
        return qu;
    }


    @Override
    public void deleteQuestion(String idq) {
        questionrepo.deleteById(idq);
    }

    @Override
    public Question affichDetailQuestion(String idq) {
        Question q= questionrepo.findById(idq).orElse(null);
        return q;
    }

    @Override
    public List<Question> afficherQuestions() {
        return questionrepo.findAll();
    }
    @Override
    public  String hashbadword(String c, String idqr, String idu) {

        List<String> badWords = new ArrayList<>();
        badWords.add("Hello");
        badWords.add("World");
        badWords.add("Java");
        badWords.add("re");

        // Split the paragraph into words using whitespace as the delimiter
        String[] words = c.split("\\s+");

        // Iterate through the bad words list
        for (String badWord : badWords) {
            // Iterate through each word in the paragraph
            for (int i = 0; i < words.length; i++) {
                // If the word matches the bad word, replace it with ***
                if (words[i].equalsIgnoreCase(badWord)) {
                    this.ajouterbadword(idqr,idu,badWord);
                    words[i] = "***";
                }
            }
        }

        // Reconstruit le texte modifié en rejoignant les mots du tableau avec des espaces
        String modifiedParagraph = String.join(" ", words);

        // Retourne la version modifiée du texte avec les mots interdits remplacés par "***"
        return modifiedParagraph;
    }

    @Override
    public List<Question> getQuestionsByContenue(String name) {
        List<Question> search =new ArrayList<Question>();
        // TODO Auto-generated method stub
        List<Question> questions=questionrepo.findAll();
        for(Question a : questions) {
            if(a.getContenue().contains(name)) {
                search.add(a);
            }
        }
        return search;
    }

    public SignalBadword ajouterbadword(String idq,String idu,String s) {
        SignalBadword sbw=new SignalBadword();
        Question q = this.affichDetailQuestion(idq);
        User u = userepo.findById(idu).orElse(null);
        if(u!=null&&q!=null){
            sbw.setUser(u);
            sbw.setQuestion(q);
            sbw.setBadword(s);

        }
        return sbwserv.save(sbw);
    }

}
