package me.ahmedashour.skiller.datamanagers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import me.ahmedashour.skiller.constants.FirebaseKeys
import me.ahmedashour.skiller.model.data.Skill
import timber.log.Timber
import java.util.*

class SkillsDataManager {
    private val skillsLiveData: MutableLiveData<List<Skill?>> = MutableLiveData()
    private var temp = mutableListOf<Skill>()
    private val mDatabaseSkills = FirebaseDatabase.getInstance().reference
            .child(FirebaseKeys.CHILD_SKILLS)
    val skillsFromDatabase: LiveData<List<Skill?>>
        get() {
            mDatabaseSkills.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    temp = mutableListOf()
                    for (dataSnapshot1 in dataSnapshot.children) {
                        val skill = dataSnapshot1.getValue(Skill::class.java)
                        skill?.let { temp.add(it) }
                    }
                    skillsLiveData.value = temp
                    Timber.d("Skills Count: %s", skillsLiveData.value!!.size)
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            return skillsLiveData
        }
}