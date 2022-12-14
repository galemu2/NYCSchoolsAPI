package com.ctrlaccess.a20220905_girumlemu_nycschools.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ctrlaccess.a20220905_girumlemu_nycschools.databinding.LoadStateHeaderFooterBinding

class SchoolsLoadStateAdaptor(private val retry: () -> Unit) :
    LoadStateAdapter<SchoolsLoadStateAdaptor.SchoolsLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): SchoolsLoadStateViewHolder {

        val binding = LoadStateHeaderFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SchoolsLoadStateViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: SchoolsLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }


    inner class SchoolsLoadStateViewHolder(
        private val binding: LoadStateHeaderFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {

            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}
