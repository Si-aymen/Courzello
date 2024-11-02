package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.*;
import com.pidev.backend.Repository.ChapterRepository;
import com.pidev.backend.Repository.CourseRepository;
import com.pidev.backend.Service.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private EmailSenderService senderService;

    ChapterRepository chapterRepository ;
    CourseRepository courseRepository ;
    @Override
    public Chapter addChapter(Chapter chapter) {

        senderService.sendSimpleEmail("rahali.aymen2001@gmail.com",
                "Courzello Classrooms ",
                "A new Classroom was added \n" +
                        "chapter ID:" + chapter.getId()+"\n"+
                        "chapter Name:" + chapter.getChapterName()+"\n"+
                        "chapter Duration  :" + chapter.getDuration()+"\n"+
                        "chapter Courses:" + chapter.getCourses()+"\n"
        );
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



    public List<Chapter> GetChapterByCourse(String classroomId) {

        List<Chapter> Chapter = chapterRepository.findAll();
        List<Chapter> ChapterInClassroom = new ArrayList<>();
        for (Chapter chapter : Chapter) {
            for (Course course : chapter.getCourses()) {
                if (course.getId().equals(classroomId)) {
                    ChapterInClassroom.add(chapter);
                    break;
                }
            }
        }
        return ChapterInClassroom;


    }
}
