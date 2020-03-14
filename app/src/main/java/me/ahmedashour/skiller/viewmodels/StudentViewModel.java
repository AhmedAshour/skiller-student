package me.ahmedashour.skiller.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;
import android.util.Log;


import java.util.HashMap;

import me.ahmedashour.skiller.datamanagers.StudentDataManager;
import me.ahmedashour.skiller.model.users.Student;

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