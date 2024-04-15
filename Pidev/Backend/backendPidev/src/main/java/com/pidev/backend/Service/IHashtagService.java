package com.pidev.backend.Service;

import com.pidev.backend.Entity.Hashtag;

import java.util.List;

public interface IHashtagService {
    public boolean ajouthashtag(Hashtag h);
    public void deletehashtag(String id);
    public List<Hashtag> affichethashtags();
}
