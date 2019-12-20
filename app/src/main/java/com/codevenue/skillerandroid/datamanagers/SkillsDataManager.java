package com.codevenue.skillerandroid.datamanagers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.codevenue.skillerandroid.constants.FirebaseKeys;
import com.codevenue.skillerandroid.model.misc.Skill;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SkillsDataManager {
    private MutableLiveData<List<Skill>> skillsLiveData;
    private List<Skill> temp;
    private DatabaseReference mDatabaseSkills;

    public SkillsDataManager(){
        skillsLiveData = new MutableLiveData<>();
        temp = new ArrayList<>();
        mDatabaseSkills = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_SKILLS);

        mDatabaseSkills.keepSynced(true);
    }

    public LiveData<List<Skill>> getSkillsFromDatabase(){
        mDatabaseSkills.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Skill skill;
                temp.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    skill = dataSnapshot1.getValue(Skill.class);
                    temp.add(skill);
                    Log.d("SkillsDataManager", skill.getSkillTitle());
                    Log.d("SkillsDataManager", skill.getBackgroundImage());
                }
                skillsLiveData.setValue(temp);
                Log.d("SkillsDataManager", skillsLiveData.getValue().get(0).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return skillsLiveData;
    }
}
