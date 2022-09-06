package com.ctrlaccess.a20220905_girumlemu_nycschools.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ctrlaccess.a20220905_girumlemu_nycschools.api.SchoolsApi
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.SchoolsPagingSource
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.SchoolsRepository

class SchoolsViewModel : ViewModel() {

    private val currentQuery = MutableLiveData("")
/*    val schools = currentQuery.switchMap {
        repository.getSchools().cachedIn(viewModelScope)
    }*/

    val schools = Pager(
        PagingConfig(pageSize = 20)
    ) {
        SchoolsPagingSource(SchoolsApi.getInstance())
    }.flow
        .cachedIn(viewModelScope)

}

/*  todo delete
class SchoolsViewModelFactory(private val repository: SchoolsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchoolsViewModel::class.java))
            return modelClass.getConstructor(SchoolsRepository::class.java)
                .newInstance(repository) as T
        throw IllegalArgumentException("Unknown viewModel class")
    }
}*/
