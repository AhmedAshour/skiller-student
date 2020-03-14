package me.ahmedashour.skiller.viewmodels.lessons;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;
import android.util.Log;

import java.util.List;

import me.ahmedashour.skiller.datamanagers.lessons.LessonsRequestsDataManager;
import me.ahmedashour.skiller.model.misc.Lesson;

public class LessonsRequestsViewModel extends ViewModel {

    private MediatorLiveData<List<Lesson>> lessonMediatorLiveData;
    private LessonsRequestsDataManager lessonsDataManager;


    public LessonsRequestsViewModel(){
        Log.d("CREATIONmmmm", "CREATED");

        this.lessonMediatorLiveData = new MediatorLiveData<>();
        this.lessonsDataManager = new LessonsRequestsDataManager();
    }


    public MediatorLiveData<List<Lesson>> getLessons(String childLessonsUpcoming) {
        if(lessonMediatorLiveData.getValue() == null){
            lessonMediatorLiveData.addSource(lessonsDataManager.getLessons(childLessonsUpcoming), new Observer<List<Lesson>>() {
                @Override
                public void onChanged(@Nullable List<Lesson> lessons) {
                    lessonMediatorLiveData.setValue(lessons);
                }
            });
        }
        return lessonMediatorLiveData;
    }

}
