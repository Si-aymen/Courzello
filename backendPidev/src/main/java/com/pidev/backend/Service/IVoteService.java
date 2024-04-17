package com.pidev.backend.Service;

import com.pidev.backend.Entity.Vote;

public interface IVoteService {
    public Vote ajoutdeletevote(Vote v, String idu , String idq);
    public int nbvotebyquest(String idq);

}
