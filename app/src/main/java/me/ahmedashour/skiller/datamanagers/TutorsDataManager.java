package me.ahmedashour.skiller.datamanagers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import me.ahmedashour.skiller.constants.FirebaseKeys;
import me.ahmedashour.skiller.model.Course;
import me.ahmedashour.skiller.model.misc.Experience;
import me.ahmedashour.skiller.model.misc.Feedback;
import me.ahmedashour.skiller.model.users.Tutor;

public class TutorsDataManager {
    private final String TAG = getClass().getSimpleName();
    private MutableLiveData<List<Tutor>> tutorsLiveData;
    private MutableLiveData<Tutor> tutorInfoLiveData;
    private MutableLiveData<List<Feedback>> feedbacksLiveData;
    private MutableLiveData<List<Experience>> experiencesLiveData;
    private MutableLiveData<List<Course>> coursesLiveData;
    private List<Tutor> tempTutors;
    private List<Feedback> tempFeedbacks;
    private List<Experience> tempExperiences;
    private List<Course> tempCourseList;
    private DatabaseReference ref;
    private Tutor tutor;
    private FirebaseUser user;
    private String tutorUid = "";
    private DatabaseReference mDatabaseTutors;
    private DatabaseReference mDatabaseInnerSkills;


    public TutorsDataManager() {
        tutorsLiveData = new MutableLiveData<>();
        tutorInfoLiveData = new MutableLiveData<>();
        feedbacksLiveData = new MutableLiveData<>();
        experiencesLiveData = new MutableLiveData<>();
        coursesLiveData = new MutableLiveData<>();
        tempTutors = new ArrayList<>();
        tempFeedbacks = new ArrayList<>();
        tempExperiences = new ArrayList<>();
        tempCourseList = new ArrayList<>();

        mDatabaseTutors = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_TUTORS);
        mDatabaseTutors.keepSynced(true);
        mDatabaseInnerSkills = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_INNER_SKILLS);
        mDatabaseInnerSkills.keepSynced(true);

        user = FirebaseAuth.getInstance().getCurrentUser();
        tutorUid = user.getUid();

    }

    public MutableLiveData<List<Tutor>> getTutorsFromDatabase() {
        mDatabaseTutors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.getValue().toString());
                tempTutors.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Tutor tutor = retrieveTutorFromDatabase(dataSnapshot1);
                    tempTutors.add(tutor);
                }
                tutorsLiveData.setValue(tempTutors);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return tutorsLiveData;
    }

    public MutableLiveData<List<Tutor>> getTopTenTutorsFromDatabase() {
        Query query = mDatabaseTutors.orderByChild(FirebaseKeys.Tutor.KEY_CHILD_TUTORS_NUM_EXPERIENCE_HOURS).limitToFirst(10);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempTutors.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.d(TAG, data.toString());
                    onDataChanges(data);
                }
                tutorsLiveData.setValue(tempTutors);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database error: ", databaseError.getMessage());
            }
        });
        return tutorsLiveData;
    }


    public MutableLiveData<List<Tutor>> getTutorsFilteredFromDatabase(String filterString) {
        DatabaseReference filteredTutors = mDatabaseInnerSkills.child(filterString);

        filteredTutors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempTutors.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String value = dataSnapshot1.getValue().toString();
                    Log.d("DATASNAP", value);

                    ref = FirebaseDatabase.getInstance().getReference().child("tutors").child(value);
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            onDataChanges(dataSnapshot);
                            tutorsLiveData.setValue(tempTutors);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d("INNER ERROR", databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("OUTER ERROR", databaseError.getMessage());
            }
        });
        return tutorsLiveData;
    }


    public LiveData<Tutor> getTutorInfoFromDatabase(String tutorUid) {
        DatabaseReference tutorInfoRef = mDatabaseTutors.child(tutorUid);
        tutorInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Tutor tutor = retrieveTutorFromDatabase(dataSnapshot);
                tutorInfoLiveData.setValue(tutor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return tutorInfoLiveData;
    }

    public LiveData<List<Feedback>> getFeedbacks(String tutorID) {
        DatabaseReference feedbacksRef = mDatabaseTutors.child(tutorID).child(FirebaseKeys.Tutor.User.CHILD_USER_FEEDBACK_LIST);
        feedbacksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempFeedbacks.clear();
                Feedback feedback;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    feedback = ds.getValue(Feedback.class);
                    tempFeedbacks.add(feedback);
                    Log.d("feedbacks database", tempFeedbacks.get(0).toString());
                }
                feedbacksLiveData.setValue(tempFeedbacks);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("ERROR database", databaseError.getMessage());

            }
        });
        return feedbacksLiveData;
    }

    public LiveData<List<Experience>> getExperiences(String tutorUid) {
        DatabaseReference experiencesRef = mDatabaseTutors.child(tutorUid).child(FirebaseKeys.Tutor.User.CHILD_USER_EXPERIENCE);
        experiencesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempExperiences.clear();
                Experience experience;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    experience = ds.getValue(Experience.class);
                    tempExperiences.add(experience);
                }
                experiencesLiveData.setValue(tempExperiences);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("ERROR database", databaseError.getMessage());
            }
        });
        return experiencesLiveData;
    }

    public LiveData<List<Course>> getCoursesFromDatabase(String tutorID) {
        DatabaseReference coursesRef = mDatabaseTutors.child(tutorID).child(FirebaseKeys.Tutor.User.CHILD_USER_COURSES_LIST);
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempCourseList.clear();
                Course course;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    course = ds.getValue(Course.class);
                    tempCourseList.add(course);
                }
                coursesLiveData.setValue(tempCourseList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return coursesLiveData;
    }

    private void getMapReady(Map<String, Object> map, String key) {
        List<Object> expList = Collections.singletonList(map.get(key));
        String listString = TextUtils.join(", ", expList);
        if (listString.charAt(0) != '[') {
            map.put(key, expList);
        }
        Log.d(TAG, "onDataChange: String " + listString);
        Log.d(TAG, "onDataChange: MAP AFTER" + map.get(key));
    }

    private Tutor retrieveTutorFromDatabase(DataSnapshot dataSnapshot) {
        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
        Log.d(TAG, "onDataChange: " + map.toString());

        getMapReady(map, "experienceList");
        getMapReady(map, "feedBacksList");
        getMapReady(map, "coursesList");

        String jsonResponse = null;
        try {
            jsonResponse = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onDataChange: json :: " + jsonResponse);
        Gson gson = new Gson();
        tutor = gson.fromJson(jsonResponse, Tutor.class);
        return tutor;
    }

    private void onDataChanges(DataSnapshot dataSnapshot) {
        Tutor tutor = retrieveTutorFromDatabase(dataSnapshot);
        String id = tutor.getDatabaseReference().trim();
        int i;
        for (i = 0; i < tempTutors.size(); i++) {
            if (tempTutors.get(i).getDatabaseReference().trim().equals(id)) {
                tempTutors.remove(i);
                tempTutors.add(tutor);
                break;
            }
        }
        if (i == tempTutors.size()) {
            tempTutors.add(tutor);
        }
    }
    public void setTutorRatingInDB(String tutorId, final float rating) {
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("tutors")
                .child(tutorId).child("rating");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String strRate = dataSnapshot.getValue().toString();
                double doubleRate = (double) rating;
                double newRate = (doubleRate + Double.parseDouble(strRate)) / 2;
                ref.setValue(String.valueOf(newRate));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addToFeedbacksListInDB(String tutorUid, float rating, String strReview) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("tutors")
                .child(tutorUid).child("feedBacksList");
        reference.push().setValue(new Feedback(String.valueOf(rating), strReview));
    }
}