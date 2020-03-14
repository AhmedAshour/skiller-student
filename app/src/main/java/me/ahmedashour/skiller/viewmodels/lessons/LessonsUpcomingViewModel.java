package me.ahmedashour.skiller.viewmodels.lessons;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;


import java.util.List;

import me.ahmedashour.skiller.datamanagers.lessons.LessonsUpcomingDataManager;
import me.ahmedashour.skiller.model.misc.Lesson;

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
