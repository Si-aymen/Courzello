package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Question;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Entity.Vote;
import com.pidev.backend.Service.IVoteService;
import com.pidev.backend.Repository.QuestionRepository;
import com.pidev.backend.Repository.UserRepository;
import com.pidev.backend.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements IVoteService {
    @Autowired
    UserRepository userrepo;
    @Autowired
    VoteRepository voterepo;
    @Autowired
    QuestionRepository questionrepo;
    @Override
    public Vote ajoutdeletevote(Vote v, String idu, String idq) {
        Question question = questionrepo.findById(idq).orElse(null);
        User user = userrepo.findById(idu).orElse(null);
        if (user != null && question != null) {
            Vote vote = voterepo.findByQuestionAndUser(idq, idu);
            if (vote != null ) {
                if(user.getVotes() != null && question.getVotes() != null){
                    // Remove the vote from user's votes list

                   for(Vote vo :user.getVotes()){
                       if(vo.equals(vote)){
                           user.getVotes().remove(vo);
                           userrepo.save(user);
                           break;
                       }
                   }


                    for(Vote vo :question.getVotes()){
                        if(vo.equals(vote)){
                            question.getVotes().remove(vo);
                            System.out.println(question.getVotes().size());
                            questionrepo.save(question);
                            break;
                        }
                    }

                    // Remove the vote from question's votes list


                    // Remove the vote entity
                    voterepo.delete(vote);
                }
                return vote;
            } else {
                voterepo.save(v);
                if(user.getVotes() != null){
                    user.getVotes().add(v);
                    userrepo.save(user);
                }else{
                    user.getVotes().add(v);
                    userrepo.save(user);
                }
                if(question.getVotes() != null){
                    question.getVotes().add(v);
                    questionrepo.save(question);
                }else{
                    question.getVotes().add(v);
                    questionrepo.save(question);
                }




                v.setUser(user);
                v.setQuestion(question);

                // Save the new vote entity
                return voterepo.save(v);

            }

        }
        return v;

    }






    @Override
    public int nbvotebyquest(String idq) {
        Question ques =questionrepo.findById(idq).orElse(null);

        if (ques != null && ques.getVotes() != null) {
            return ques.getVotes().size();
        }
        return  0;
    }





}
