package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Vote;

import com.pidev.backend.ServiceImpl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    VoteServiceImpl voteserv;

    @PostMapping("/add-delete-vote/{id-use}/{id-question}")
    @ResponseBody
    public Vote ajoutdeletevote(@RequestBody Vote p, @PathVariable("id-question") String idq, @PathVariable("id-use") String idu) {
         return voteserv.ajoutdeletevote(p,idu,idq);

    }
    @GetMapping("/nbr-vote/{id-question}")
    @ResponseBody
    public int ajoutdeletevote( @PathVariable("id-question") String idq) {
         return voteserv.nbvotebyquest(idq);

    }
}
