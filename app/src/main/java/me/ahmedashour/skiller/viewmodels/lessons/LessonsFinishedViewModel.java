package me.ahmedashour.skiller.viewmodels.lessons;


import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.ahmedashour.skiller.MyCallback;
import me.ahmedashour.skiller.datamanagers.lessons.LessonsFinishedDataManager;
import me.ahmedashour.skiller.model.misc.Lesson;

public class LessonsFinishedViewModel extends ViewModel {
    private MediatorLiveData<List<Lesson>> lessonMediatorLiveData;
    private LessonsFinishedDataManager lessonsDataManager;


    public LessonsFinishedViewModel(){
        Log.d("CREATION", "CREATED");
        this.lessonMediatorLiveData = new MediatorLiveData<>();
        this.lessonsDataManager = new LessonsFinishedDataManager();
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

    public void getIsDoneStudent(String key, MyCallback callback) {
        Log.d("VM", "vmmmmmmmmm");
        lessonsDataManager.getIsDoneStudentFromDB(key, callback);
    }

    public void setIsDoneStudent(String key, Boolean isDoneStudent) {
        lessonsDataManager.setIsDoneStudentInDB(key, isDoneStudent);
    }

}
