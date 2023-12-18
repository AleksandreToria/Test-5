package com.example.test5.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test5.BaseFragment
import com.example.test5.adapter.Adapter
import com.example.test5.adapter.AdapterTwo
import com.example.test5.databinding.FragmentMainBinding
import com.example.test5.dataclass.NewCourse
import com.example.test5.viewmodel.YourViewModel
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var adapterOne: Adapter
    private lateinit var adapterTwo: AdapterTwo
    private val viewModel: YourViewModel by viewModels()

    override fun bindViewActionListener() {
    }

    override fun setUp() {
        recyclerViewOneSetup()
        recyclerViewTwoSetup()
    }

    private fun recyclerViewOneSetup() {
        adapterOne = Adapter()

        val horizontalLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerOne.layoutManager = horizontalLayoutManager

        binding.recyclerOne.adapter = adapterOne

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dataFlow.collect { response ->
                    response?.let {
                        val itemsOne = mutableListOf<NewCourse>()

                        itemsOne.addAll(it.newCourse.filterIsInstance<NewCourse>())

                        adapterOne.submitList(itemsOne)
                    }
                }
            }
        }
    }

    private fun recyclerViewTwoSetup() {
        adapterTwo = AdapterTwo()

        val verticalLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerTwo.layoutManager = verticalLayoutManager

        binding.recyclerTwo.adapter = adapterTwo

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dataFlow.collect { response ->
                    response?.let {
                        val itemsTwo = it.activeCourse

                        adapterTwo.submitList(itemsTwo)
                    }
                }
            }
        }
    }


}



