package com.pidev.backend.Service;

import com.pidev.backend.Entity.Reponse;

import java.util.List;

public interface IReponseService {
    public Reponse ajoutReponse(Reponse q,String idq ,String iduse);
    public Reponse updateReponse(String idq ,Reponse q);

    public void deleteReponse(String idq);

    public Reponse affichDetailReponse(String idq);

    public List<Reponse> afficherReponse();
    public List<Reponse> afficherReponsebypost(String idq);
}
