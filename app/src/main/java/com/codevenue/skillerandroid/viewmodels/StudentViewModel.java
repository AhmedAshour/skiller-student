package com.codevenue.skillerandroid.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codevenue.skillerandroid.datamanagers.StudentDataManager;
import com.codevenue.skillerandroid.model.users.Student;

import java.util.HashMap;

public class StudentViewModel extends ViewModel {

    private String TAG = StudentViewModel.class.getSimpleName();
    private StudentDataManager studentDataManager;
    private MediatorLiveData<Student> studentMediatorLiveData;

    public StudentViewModel() {
        this.studentDataManager = new StudentDataManager();
        studentMediatorLiveData = new MediatorLiveData<>();
    }
    public LiveData<Student> getStudentData(){
        if (studentMediatorLiveData.getValue() == null){
            studentMediatorLiveData.addSource(studentDataManager.getStudentDataFromDatabase(), new Observer<Student>() {
                @Override
                public void onChanged(@Nullable Student student) {
                    Log.d(TAG, student+"");
                    studentMediatorLiveData.setValue(student);
                }
            });
        }
        return studentMediatorLiveData;
    }

    public void writeToCurrentStudent(HashMap<String, Object> studentMap) {
        studentDataManager.writeToCurrentStudent(studentMap);
    }

    public void updateStudentProfile(Student updatedStudent) {
        studentDataManager.updateStudentProfileToDatabase(updatedStudent);
    }
}