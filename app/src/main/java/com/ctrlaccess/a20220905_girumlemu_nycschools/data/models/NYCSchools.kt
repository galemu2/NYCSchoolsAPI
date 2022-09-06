package com.ctrlaccess.a20220905_girumlemu_nycschools.data.models

import com.google.gson.annotations.SerializedName

data class NYCSchools(
    val borough: String,  // important
    val dbn: String,  // important
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String, // important
    @SerializedName("school_name")
    val schoolName: String // important

) {
    override fun toString(): String {
        return "dbn:$dbn\n" +
                "HighSchool:$schoolName\n" +
                "Address:$primaryAddressLine1\n" +
                "Borough:$borough"
    }
}
