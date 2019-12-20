package com.codevenue.skillerandroid.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.codevenue.skillerandroid.datamanagers.LessonsDataManager;
import com.codevenue.skillerandroid.model.misc.Lesson;

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
