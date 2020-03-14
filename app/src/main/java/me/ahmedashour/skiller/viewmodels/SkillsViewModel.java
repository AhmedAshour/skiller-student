package me.ahmedashour.skiller.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import java.util.List;

import me.ahmedashour.skiller.datamanagers.SkillsDataManager;
import me.ahmedashour.skiller.model.misc.Skill;

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
