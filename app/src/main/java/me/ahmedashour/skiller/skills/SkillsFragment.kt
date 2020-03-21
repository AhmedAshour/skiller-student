package me.ahmedashour.skiller.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import me.ahmedashour.skiller.databinding.FragmentSkillsBinding
import timber.log.Timber

class SkillsFragment : Fragment() {
    private val viewModel: SkillsViewModel by viewModels()
    private var skillsAdapter = SkillsAdapter()
    private lateinit var binding: FragmentSkillsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSkillsBinding.inflate(inflater, container, false)

        initViews()
        observers()

        return binding.root
    }

    private fun observers() {
        viewModel.skills.observe(viewLifecycleOwner, Observer { skillsList ->
            Timber.i("Skills List Count:${skillsList?.size}")

            skillsAdapter.submitList(skillsList)
        })
    }

    private fun initViews() {
        initSkillsRecyclerView()
    }

    private fun initSkillsRecyclerView() {
        binding.rvSkills.adapter = skillsAdapter
    }

}