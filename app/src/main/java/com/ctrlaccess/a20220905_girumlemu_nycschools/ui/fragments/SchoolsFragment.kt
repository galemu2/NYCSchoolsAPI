package com.ctrlaccess.a20220905_girumlemu_nycschools.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.ctrlaccess.a20220905_girumlemu_nycschools.R
import com.ctrlaccess.a20220905_girumlemu_nycschools.adapters.SchoolsAdaptor
import com.ctrlaccess.a20220905_girumlemu_nycschools.adapters.SchoolsLoadStateAdaptor
import com.ctrlaccess.a20220905_girumlemu_nycschools.databinding.FragmentSchoolsBinding
import com.ctrlaccess.a20220905_girumlemu_nycschools.ui.SchoolsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SchoolsFragment : Fragment(R.layout.fragment_schools) {

    private val viewModel: SchoolsViewModel by viewModels()

    private var _binding: FragmentSchoolsBinding? = null
    private val binding: FragmentSchoolsBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSchoolsBinding.bind(view)

        val schoolsAdaptor = SchoolsAdaptor()

        binding.recyclerViewHighSchools.setHasFixedSize(true)
        binding.recyclerViewHighSchools.adapter = schoolsAdaptor.withLoadStateHeaderAndFooter(
            header = SchoolsLoadStateAdaptor { schoolsAdaptor.retry() },
            footer = SchoolsLoadStateAdaptor { schoolsAdaptor.retry() }
        )

        binding.buttonReload.setOnClickListener { schoolsAdaptor.retry() }


        // submit data to adaptor
        lifecycleScope.launch {
            viewModel.schools.collectLatest { pagingData ->
                schoolsAdaptor.submitData(pagingData = pagingData)
            }
        }

        schoolsAdaptor.addLoadStateListener { loadState ->

            binding.apply {
                val currentState = loadState.source.refresh
                progressBar.isVisible = currentState is LoadState.Loading
                recyclerViewHighSchools.isVisible = currentState is LoadState.NotLoading
                textViewError.isVisible = currentState is LoadState.Error
                buttonReload.isVisible = currentState is LoadState.Error

                if (currentState is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    schoolsAdaptor.itemCount < 1
                ) {
                    recyclerViewHighSchools.isVisible = false
                    textViewNoResults.isVisible = true
                } else {
                    textViewNoResults.isVisible = false

                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
