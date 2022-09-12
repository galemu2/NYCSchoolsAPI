package com.ctrlaccess.a20220905_girumlemu_nycschools.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ctrlaccess.a20220905_girumlemu_nycschools.api.SchoolsServices
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.NYCSchools

class SchoolsPagingSource(
    private val api: SchoolsServices,
) : PagingSource<Int, NYCSchools>() {

    private val STARTING_PAGE = 1
    private val OFFSET_VALUE = 20

    override fun getRefreshKey(state: PagingState<Int, NYCSchools>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NYCSchools> {

        return try {
            val offset = params.key ?: 1


            val result = api.getAllSchools(limit = OFFSET_VALUE, offset = offset)
            LoadResult.Page(
                data = result,
                prevKey = if (offset == STARTING_PAGE) null else offset - OFFSET_VALUE,
                nextKey = if (result.isNullOrEmpty()) null else offset + OFFSET_VALUE
            )

        } catch (e: Exception) {
            Log.d("TAG", "load: ${e.message}")
            LoadResult.Error(e)

        }


    }
}