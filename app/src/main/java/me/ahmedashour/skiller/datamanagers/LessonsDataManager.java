package me.ahmedashour.skiller.datamanagers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.ahmedashour.skiller.constants.FirebaseKeys;
import me.ahmedashour.skiller.model.misc.Lesson;

public class LessonsDataManager {
    //Database references
    private DatabaseReference mDatabaseRequests;
    private DatabaseReference mDatabaseUpcoming;
    private DatabaseReference mDatabaseFinished;

    //Mutable live data

    public LessonsDataManager(){
        mDatabaseRequests = FirebaseDatabase.getInstance().getReference().child(FirebaseKeys.CHILD_LESSONS_REQUESTS);
        mDatabaseUpcoming = FirebaseDatabase.getInstance().getReference().child(FirebaseKeys.CHILD_LESSONS_UPCOMING);
        mDatabaseFinished = FirebaseDatabase.getInstance().getReference().child(FirebaseKeys.CHILD_LESSONS_FINISHED);

        mDatabaseRequests.keepSynced(true);
        mDatabaseUpcoming.keepSynced(true);
        mDatabaseFinished.keepSynced(true);
    }

    public void writeLessonRequestToDatabase(Lesson lesson){
        String hashId = mDatabaseRequests.push().getKey();
        lesson.setHashId(hashId);
        mDatabaseRequests.child(hashId).setValue(lesson);
    }
    public void deleteRequestFromDB(String key) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_REQUESTS).child(key);
        reference.removeValue();
    }
    public void deleteUpcomingFromDB(String key) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_UPCOMING).child(key);
        reference.removeValue();
    }



}