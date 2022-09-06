package com.ctrlaccess.a20220905_girumlemu_nycschools.api

import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.NYCSchools
import com.ctrlaccess.a20220905_girumlemu_nycschools.data.models.SatResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsServices {

    // https://data.cityofnewyork.us/resource/s3k6-pzi2.json?$limit=20&$offset=0
    @GET("s3k6-pzi2.json")
    suspend fun getAllSchools(
        @Query("\$limit") limit: Int = 2,
        @Query("\$offset") offset: Int = 0
    ): ArrayList<NYCSchools>


    @GET("f9bf-2cp4.json")
    suspend fun getSatResult(
        @Query("dbn") dbn: String
    ): List<SatResult>


    /*
    *
    *
    * todo deleat
    @GET("s3k6-pzi2.json")
    List<HighSchool> getAllHighSchools(
            @Query("$limit") int limit,
            @Query("$offset") int offset
    );

    // https://data.cityofnewyork.us/resource/f9bf-2cp4.json?dbn=01M450
    @GET("f9bf-2cp4.json")
    Call<List<SATResult>> getSATResult(@Query("dbn") String dbn);
    *
    * */

}