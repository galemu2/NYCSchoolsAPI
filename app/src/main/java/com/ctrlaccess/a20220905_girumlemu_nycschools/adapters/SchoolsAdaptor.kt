package com.ctrlaccess.a20220905_girumlemu_nycschools.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.NYCSchools
import com.ctrlaccess.a20220905_girumlemu_nycschools.databinding.ItemSchoolBinding

class SchoolsAdaptor(
    private val listener: OnItemClickListener,
    diffCallBack: DiffUtil.ItemCallback<NYCSchools> = object : DiffUtil.ItemCallback<NYCSchools>() {
        override fun areItemsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            return oldItem.dbn == newItem.dbn
        }

        override fun areContentsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            return oldItem == newItem
        }

    }
) : PagingDataAdapter<NYCSchools, SchoolsAdaptor.HighSchoolViewHolder>(diffCallback = diffCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HighSchoolViewHolder {
        val binding = ItemSchoolBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return HighSchoolViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HighSchoolViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(dbn: String)
    }

    inner class HighSchoolViewHolder(private val binding: ItemSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let {
                        listener.onItemClicked(it.dbn)
                    }
                }
            }
        }

        fun bind(NYCSchoolsItem: NYCSchools) {
            binding.apply {
                textViewHighSchoolName.text = NYCSchoolsItem.schoolName
                textViewHighSchoolAddress.text = NYCSchoolsItem.primaryAddressLine1
                textViewHighSchoolBorough.text = NYCSchoolsItem.borough
            }
        }

    }
}