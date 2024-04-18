package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Question;
import com.pidev.backend.ServiceImpl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionServiceImpl questionserv;
    @PostMapping("/add-question/{id-user}")
    @ResponseBody
    public Question ajoutquestion(@RequestBody Question p,@PathVariable("id-user") String idu) {
        return questionserv.ajoutQuestion(p,idu);

    }
    @GetMapping("/get-questionbyid/{id-question}")
    @ResponseBody
    public Question getquestionbyid(@PathVariable("id-question") String idq) {
        return questionserv.affichDetailQuestion(idq);

    }
    @GetMapping("/get-questions")
    @ResponseBody
    public List<Question> afficherlesquestions() {
        return questionserv.afficherQuestions();

    }
    @GetMapping("/get-questions-by-contenue")
    @ResponseBody
    public List<Question> affichQuestionsByName(@RequestParam("contenue") String contenue)
    {
        return questionserv.getQuestionsByContenue(contenue);

    }
    @PutMapping("/update-question/{id-question}")
    @ResponseBody
    public Question upadatequestion(@RequestBody Question t, @PathVariable("id-question") String idq) {
        return questionserv.updateQuestion(idq,t);

    }
    @DeleteMapping("/delete-question/{id-question}")
    @ResponseBody
    public void deletequestion(@PathVariable("id-question") String idq) {
         questionserv.deleteQuestion(idq);

    }
}
