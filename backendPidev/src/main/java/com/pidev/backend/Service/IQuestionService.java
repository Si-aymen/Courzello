package com.pidev.backend.Service;

import com.pidev.backend.Entity.Question;

import java.util.List;

public interface IQuestionService {
     public Question ajoutQuestion(Question q,String idu);
     public Question updateQuestion(String idq ,Question q);

     public void deleteQuestion(String idq);

     public Question affichDetailQuestion(String idq);

     public List<Question> afficherQuestions();
     public  String hashbadword(String c, String idqr, String idu);
     public List<Question> getQuestionsByContenue(String name);
     //rech

}
