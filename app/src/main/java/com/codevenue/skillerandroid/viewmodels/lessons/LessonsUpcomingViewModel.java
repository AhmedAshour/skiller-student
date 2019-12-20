package com.codevenue.skillerandroid.viewmodels.lessons;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.codevenue.skillerandroid.datamanagers.LessonsDataManager;
import com.codevenue.skillerandroid.datamanagers.lessons.LessonsUpcomingDataManager;
import com.codevenue.skillerandroid.model.misc.Lesson;

import java.util.List;

public class LessonsUpcomingViewModel extends ViewModel {
    private MediatorLiveData<List<Lesson>> lessonMediatorLiveData;
    private LessonsUpcomingDataManager lessonsDataManager;


    public LessonsUpcomingViewModel(){
        this.lessonMediatorLiveData = new MediatorLiveData<>();
        this.lessonsDataManager = new LessonsUpcomingDataManager();
    }


    public MediatorLiveData<List<Lesson>> getLessons(String childLessonsUpcoming) {
        if(lessonMediatorLiveData.getValue() == null){
            lessonMediatorLiveData.addSource(lessonsDataManager.getLessonsFromDatabase(childLessonsUpcoming), new Observer<List<Lesson>>() {
                @Override
                public void onChanged(@Nullable List<Lesson> lessons) {
                    lessonMediatorLiveData.setValue(lessons);
                }
            });
        }
        return lessonMediatorLiveData;
    }

}
