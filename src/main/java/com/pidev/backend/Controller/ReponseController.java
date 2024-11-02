package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Reponse;
import com.pidev.backend.ServiceImpl.ReponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/reponse")
public class ReponseController {

    @Autowired
    ReponseServiceImpl reponseserv;

    @PostMapping("/add-Reponse/{id-use}/{id-question}")
    @ResponseBody
    public Reponse ajoutReponse(@RequestBody Reponse p,@PathVariable("id-question") String idq,@PathVariable("id-use") String idu) {
        return reponseserv.ajoutReponse(p,idq,idu);

    }
    @GetMapping("/get-Reponse/{id-Reponse}")
    @ResponseBody
    public Reponse getReponsebyid(@PathVariable("id-Reponse") String idq) {
        return reponseserv.affichDetailReponse(idq);

    }
    @GetMapping("/get-Reponse/{id-question}")
    @ResponseBody
    public List<Reponse> getReponsebyquestion(@PathVariable("id-question") String idq) {
        return reponseserv.afficherReponsebypost(idq);

    }
    @GetMapping("/get-Reponse")
    @ResponseBody
    public List<Reponse> getReponsebyid() {
        return reponseserv.afficherReponse();

    }
    @PutMapping("/update-Reponse/{id-Reponse}")
    @ResponseBody
    public Reponse upadateReponse(@RequestBody Reponse t, @PathVariable("id-Reponse") String idq) {
        return reponseserv.updateReponse(idq,t);

    }
    @DeleteMapping("/delete-Reponse/{id-Reponse}")
    @ResponseBody
    public void deleteReponse(@PathVariable("id-Reponse") String idq) {
        reponseserv.deleteReponse(idq);

    }
}
