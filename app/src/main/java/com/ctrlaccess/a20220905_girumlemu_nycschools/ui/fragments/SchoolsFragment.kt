package com.ctrlaccess.a20220905_girumlemu_nycschools.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ctrlaccess.a20220905_girumlemu_nycschools.R
import com.ctrlaccess.a20220905_girumlemu_nycschools.adapters.SchoolsAdaptor
import com.ctrlaccess.a20220905_girumlemu_nycschools.api.SchoolsApi
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.SchoolsRepository
import com.ctrlaccess.a20220905_girumlemu_nycschools.databinding.FragmentSchoolsBinding
import com.ctrlaccess.a20220905_girumlemu_nycschools.ui.SchoolsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SchoolsFragment : Fragment(R.layout.fragment_schools) {

    private val viewModel: SchoolsViewModel by viewModels() /*lazy {
        SchoolsViewModel(SchoolsRepository(SchoolsApi.getInstance()))
*//*        SchoolsViewModelFactory(SchoolsRepository(SchoolsApiClient.getInstance()))
            .create(
                SchoolsViewModel::
                class.java
            )*//*
    }*/


    private var _binding: FragmentSchoolsBinding? = null
    private val binding: FragmentSchoolsBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSchoolsBinding.bind(view)

        val schoolsAdaptor = SchoolsAdaptor()
        binding.recyclerViewHighSchools.setHasFixedSize(true)
        binding.recyclerViewHighSchools.adapter = schoolsAdaptor

        // submit data to adaptor
        lifecycleScope.launch {
            viewModel.schools.collectLatest { pagindData ->
                schoolsAdaptor.submitData(pagingData = pagindData)
            }
        }

        //  schoolsAdaptor.submitData(viewLifecycleOwner.lifecycle, pagingData)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
