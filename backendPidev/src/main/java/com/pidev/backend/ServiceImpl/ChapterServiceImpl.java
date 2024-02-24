package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Chapter;
import com.pidev.backend.Entity.Course;
import com.pidev.backend.Entity.Role;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.ChapterRepository;
import com.pidev.backend.Repository.CourseRepository;
import com.pidev.backend.Service.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    ChapterRepository chapterRepository ;
    CourseRepository courseRepository ;
    @Override
    public Chapter addChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public List<Chapter> getAllChapters() {
       return chapterRepository.findAll();
    }

    @Override
    public Chapter modifyChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public void deleteChapter(String ChapterId) {
        Chapter c = chapterRepository.findById(ChapterId).get();
        chapterRepository.delete(c);

    }

    @Override
    public void assignChapterToCourse(List<Chapter> chapterList, String idCourse) {


        Set<Chapter> chapterSet = new HashSet<>();
            Course course = courseRepository.findById(idCourse).get();
        for (Chapter s : chapterList) {
            s.getCourses().add(course);
            chapterRepository.save(s);
            course.getChapters().add(s);
            courseRepository.save(course);

        }
    }
}
