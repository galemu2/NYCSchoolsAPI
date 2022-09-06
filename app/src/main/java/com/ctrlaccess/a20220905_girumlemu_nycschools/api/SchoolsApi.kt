package com.ctrlaccess.a20220905_girumlemu_nycschools.api

import com.ctrlaccess.a20220905_girumlemu_nycschools.api.ApiUtil.BASE_URL
 import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SchoolsApi {

    companion object {
        private var INSTANCE: Retrofit? = null

        fun getInstance(): SchoolsServices {
            var tmp = INSTANCE

            if (INSTANCE == null) {
                synchronized(this) {
//                    val client = OkHttpClient().newBuilder().build()

                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                         .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    tmp = INSTANCE
                }
            }

            return tmp!!.create(SchoolsServices::class.java)
        }

    }
}

object ApiUtil {
    const val BASE_URL: String = "https://data.cityofnewyork.us/resource/"
}