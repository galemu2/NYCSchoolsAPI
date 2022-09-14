package com.ctrlaccess.a20220905_girumlemu_nycschools.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ctrlaccess.a20220905_girumlemu_nycschools.api.SchoolsApi
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.NYCSchools

class SchoolsRepository {

    fun getSchools(): Pager<Int, NYCSchools> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            SchoolsPagingSource(SchoolsApi.getInstance())
        }
    }

    suspend fun getSatResult(dbn: String) =
        SchoolsApi.getInstance().getSatResult(dbn = dbn)

}