package com.pidev.backend.Iservice;

import com.pidev.backend.Entity.SignalBadword;

import java.util.List;

public interface ISignalBadWordService {
    //public SignalBadword ajouterbadword(String idq,String idu,String s);
    public List<SignalBadword> afficheBadword();
}
