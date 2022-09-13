package com.ctrlaccess.a20220905_girumlemu_nycschools.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ctrlaccess.a20220905_girumlemu_nycschools.R
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.SatResult
import com.ctrlaccess.a20220905_girumlemu_nycschools.databinding.FragmentSatBinding
import com.ctrlaccess.a20220905_girumlemu_nycschools.ui.viewModels.SchoolsViewModel
import com.ctrlaccess.a20220905_girumlemu_nycschools.util.Resource


class SatFragment : Fragment(R.layout.fragment_sat) {

    private var _binding: FragmentSatBinding? = null
    private val binding: FragmentSatBinding
        get() = _binding!!

    private val args: SatFragmentArgs by navArgs()

    private val viewModel: SchoolsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSatBinding.bind(view)

        viewModel.getSatResults(args.dbn)

        viewModel.satResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.apply {
                        layoutError.visibility = View.GONE
                        layoutLoading.visibility = View.VISIBLE
                        layoutSatResule.visibility = View.GONE
                    }
                }
                is Resource.Success -> {
                    response.data?.let { satResults ->
                        if(satResults.isEmpty()) {
                            response.message = "Empty result"
                            errorResponse(response)
                            return@let
                        }
                        val satResult = satResults[0]
                        binding.apply {
                            layoutError.visibility = View.GONE
                            layoutLoading.visibility = View.GONE
                            layoutSatResule.visibility = View.VISIBLE

                            textViewSchool.text = satResult?.schoolName ?: "-"
                            textViewTestTakers.text = satResult.numOfSatTestTakers ?: "-"
                            textViewAvgReading.text = satResult?.satCriticalReadingAvgScore ?: "-"
                            textViewAvgWriting.text = satResult?.satWritingAvgScore ?: "-"
                            textViewAvgMath.text = satResult?.satMathAvgScore ?: "-"
                        }
                    }


                }
                is Resource.Error -> {
                    errorResponse(response)
                }
                else -> {
                    errorResponse(response)
                }
            }

        }

    }

    private fun errorResponse(response: Resource<List<SatResult>>) {
        val message = response.message
        binding.apply {
            layoutError.visibility = View.VISIBLE
            layoutLoading.visibility = View.GONE
            layoutSatResule.visibility = View.GONE
            textViewErrorMessage.text = message ?: "Unknown Error"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}