package com.pidev.backend.Service;

import com.pidev.backend.Entity.Chapter;
import com.pidev.backend.Entity.Conversation;

import java.util.List;

public interface ChapterService {

    Chapter addChapter (Chapter chapter);
    List<Chapter> getAllChapters();
    Chapter modifyChapter (Chapter chapter);
    public void deleteChapter(String ChapterId);
    public void assignChapterToCourse (List<Chapter> chapterList  , String idCourse);

}
