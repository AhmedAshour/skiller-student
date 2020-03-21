package me.ahmedashour.skiller.skills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import me.ahmedashour.skiller.datamanagers.SkillsDataManager
import me.ahmedashour.skiller.model.data.Skill

class SkillsViewModel : ViewModel() {
    private val skillsList: MediatorLiveData<List<Skill>?> = MediatorLiveData()
    val skills: LiveData<List<Skill>?>
        get() {
            if (skillsList.value == null) {
                val dataManager = SkillsDataManager()
                skillsList.addSource(dataManager.skillsFromDatabase) { skills -> skillsList.value = skills as List<Skill>? }
            }
            return skillsList
        }

}