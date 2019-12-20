package com.codevenue.skillerandroid.datamanagers;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.codevenue.skillerandroid.constants.FirebaseKeys;
import com.codevenue.skillerandroid.model.users.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class StudentDataManager {
    private String TAG = StudentDataManager.class.getSimpleName();
    private MutableLiveData<Student> studentLiveData;
    private Student student;
    private DatabaseReference mDatabaseStudent;
    private FirebaseUser user;

    public StudentDataManager(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseStudent =  FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_STUDENTS).child(user.getUid());

        mDatabaseStudent.keepSynced(true);

        studentLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Student> getStudentDataFromDatabase() {

        mDatabaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                student = dataSnapshot.getValue(Student.class);
                Log.d(TAG, student+"");
                Log.d(TAG, dataSnapshot+"");
                studentLiveData.setValue(student);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return studentLiveData;
    }

    public void writeToCurrentStudent(HashMap<String, Object> studentMap) {
        mDatabaseStudent.setValue(studentMap);
    }

    public void updateStudentProfileToDatabase(Student updatedStudent) {
        if (updatedStudent.getImageURL() != null)
            mDatabaseStudent.child(FirebaseKeys.Tutor.User.CHILD_USER_IMAGE_URL).setValue(updatedStudent.getImageURL());
        mDatabaseStudent.child(FirebaseKeys.Tutor.User.Name.CHILD_NAME)
                .child(FirebaseKeys.Tutor.User.Name.KEY_CHILD_NAME_FIRST_NAME).setValue(updatedStudent.getName().getFirstName());
        mDatabaseStudent.child(FirebaseKeys.Tutor.User.Name.CHILD_NAME)
                .child(FirebaseKeys.Tutor.User.Name.KEY_CHILD_NAME_LAST_NAME).setValue(updatedStudent.getName().getLastName());
        mDatabaseStudent.child(FirebaseKeys.Tutor.User.Contact.CHILD_CONTACT)
                .child(FirebaseKeys.Tutor.User.Address.CHILD_ADDRESS)
                .child(FirebaseKeys.Tutor.User.Address.KEY_CHILD_ADDRESS_CITY).setValue(updatedStudent.getContact().getLocation().getCity());
        mDatabaseStudent.child(FirebaseKeys.Tutor.User.Contact.CHILD_CONTACT)
                .child(FirebaseKeys.Tutor.User.Address.CHILD_ADDRESS)
                .child(FirebaseKeys.Tutor.User.Address.KEY_CHILD_ADDRESS_COUNTRY).setValue(updatedStudent.getContact().getLocation().getCountry());
        mDatabaseStudent.child(FirebaseKeys.Tutor.User.Contact.CHILD_CONTACT)
                .child(FirebaseKeys.Tutor.User.Contact.KEY_CHILD_EMAIL).setValue(updatedStudent.getContact().getEmail());
        mDatabaseStudent.child(FirebaseKeys.Tutor.User.Contact.CHILD_CONTACT)
                .child(FirebaseKeys.Tutor.User.Contact.KEY_CHILD_PHONE).setValue(updatedStudent.getContact().getPhoneNumber());
        mDatabaseStudent.child(FirebaseKeys.Tutor.User.KEY_CHILD_USER_GENDER).setValue(updatedStudent.getGender());
    }
}