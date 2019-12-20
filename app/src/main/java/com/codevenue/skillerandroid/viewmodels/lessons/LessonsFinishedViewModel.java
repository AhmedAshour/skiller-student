package com.codevenue.skillerandroid.viewmodels.lessons;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codevenue.skillerandroid.MyCallback;
import com.codevenue.skillerandroid.datamanagers.lessons.LessonsFinishedDataManager;
import com.codevenue.skillerandroid.model.misc.Lesson;

import java.util.List;

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
