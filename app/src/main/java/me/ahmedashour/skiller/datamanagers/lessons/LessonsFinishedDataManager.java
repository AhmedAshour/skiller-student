package me.ahmedashour.skiller.datamanagers.lessons;

import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.ahmedashour.skiller.MyCallback;
import me.ahmedashour.skiller.constants.FirebaseKeys;
import me.ahmedashour.skiller.model.misc.Lesson;

public class LessonsFinishedDataManager {


    private MutableLiveData<List<Lesson>> lessonLiveData;
    private List<Lesson> lessonList;
    private FirebaseAuth auth;

    //Mutable live data

    public LessonsFinishedDataManager(){
        this.lessonLiveData = new MutableLiveData<>();
        this.lessonList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
    }

    public MutableLiveData<List<Lesson>> getLessonsFromDatabase(String childLessonsUpcoming) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child(childLessonsUpcoming).orderByChild("studentUid")
                .equalTo(auth.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lessonList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    Log.d("LESSON_TITLE", ds.getKey());
                    Lesson lesson = ds.getValue(Lesson.class);
                    lesson.setHashId(ds.getKey());
                    Log.d("LESSONs_TUTOR_ID", lesson.getTutorUid());
                    lessonList.add(lesson);
                }
                lessonLiveData.setValue(lessonList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return lessonLiveData;
    }

    public void getIsDoneStudentFromDB(String key, final MyCallback callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_FINISHED).child(key).child("doneStudent");
        Log.d("IS_DONE_OUT", "DONE");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("IS_DONE", "DONE + " + dataSnapshot.getValue());
                Boolean isDoneStd = (Boolean) dataSnapshot.getValue();
                Log.d("IS_DONE_STD", "DONE + " + isDoneStd);
                callback.onCallback(isDoneStd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DB_ERROR_CANCEL", databaseError.getMessage());
            }
        });
    }

    public void setIsDoneStudentInDB(String key,Boolean isDoneStudentInDB) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_FINISHED).child(key).child("doneStudent");
        reference.setValue(isDoneStudentInDB);
    }
}
