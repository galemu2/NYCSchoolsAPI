package com.ctrlaccess.a20220905_girumlemu_nycschools.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.NYCSchools
import com.ctrlaccess.a20220905_girumlemu_nycschools.databinding.ItemSchoolBinding

class SchoolsAdaptor(
    diffCallBack: DiffUtil.ItemCallback<NYCSchools> = object : DiffUtil.ItemCallback<NYCSchools>() {
        override fun areItemsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            Log.d("diffCallBack", "areItemsTheSame: ${oldItem.dbn == newItem.dbn}")
            return oldItem.dbn == newItem.dbn
        }

        override fun areContentsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            Log.d("diffCallBack", "areContentsTheSame: ${ oldItem == newItem}")
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


    inner class HighSchoolViewHolder(private val binding: ItemSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(NYCSchoolsItem: NYCSchools) {
            binding.apply {
                textViewHighSchoolName.text = NYCSchoolsItem.schoolName
                textViewHighSchoolAddress.text = NYCSchoolsItem.primaryAddressLine1
                textViewHighSchoolBorough.text = NYCSchoolsItem.borough
            }
        }

    }
}