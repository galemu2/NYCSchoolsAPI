package com.ctrlaccess.a20220905_girumlemu_nycschools.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.SchoolsRepository

class SchoolsViewModel(private val respository: SchoolsRepository = SchoolsRepository()) :
    ViewModel() {

    val schools = respository.getSchools().flow.cachedIn(viewModelScope)

}


