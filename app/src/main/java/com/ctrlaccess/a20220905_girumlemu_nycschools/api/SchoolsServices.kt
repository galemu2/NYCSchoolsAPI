package com.ctrlaccess.a20220905_girumlemu_nycschools.api

import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.NYCSchools
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.SatResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsServices {

    // https://data.cityofnewyork.us/resource/s3k6-pzi2.json?$limit=20&$offset=0
    @GET("s3k6-pzi2.json")
    suspend fun getAllSchools(
        @Query("\$limit") limit: Int = 2,
        @Query("\$offset") offset: Int = 0
    ): List<NYCSchools>

    @GET("f9bf-2cp4.json")
    suspend fun getSatResult(
        @Query("dbn") dbn: String
    ): List<SatResult>



}