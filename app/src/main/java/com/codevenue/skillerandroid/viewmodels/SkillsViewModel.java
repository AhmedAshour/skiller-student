package com.codevenue.skillerandroid.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.codevenue.skillerandroid.datamanagers.SkillsDataManager;
import com.codevenue.skillerandroid.model.misc.Skill;

import java.util.List;

public class SkillsViewModel extends ViewModel {

    private MediatorLiveData<List<Skill>>  skillsList;

    public SkillsViewModel(){
        skillsList = new MediatorLiveData<>();
    }

    public LiveData<List<Skill>> getSkills(){
        if(skillsList.getValue() == null){
            SkillsDataManager dataManager = new SkillsDataManager();
            skillsList.addSource(dataManager.getSkillsFromDatabase(), new Observer<List<Skill>>() {
                @Override
                public void onChanged(@Nullable List<Skill> skills) {
                    skillsList.setValue(skills);
                }
            });
        }
        return skillsList;
    }
}
