package me.ahmedashour.skiller.viewmodels;

import androidx.lifecycle.ViewModel;

import me.ahmedashour.skiller.datamanagers.LessonsDataManager;
import me.ahmedashour.skiller.model.misc.Lesson;


public class LessonsViewModel extends ViewModel {

    private LessonsDataManager lessonsDataManager;


    public LessonsViewModel(){
        this.lessonsDataManager = new LessonsDataManager();
    }

    public void writeLessonRequest(Lesson lesson){
        lessonsDataManager.writeLessonRequestToDatabase(lesson);
    }
    public void deleteRequest(String key) {
        lessonsDataManager.deleteRequestFromDB(key);
    }
    public void deleteUpcoming(String key) {
        lessonsDataManager.deleteUpcomingFromDB(key);
    }
}
