package com.ctrlaccess.a20220905_girumlemu_nycschools.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ctrlaccess.a20220905_girumlemu_nycschools.R
import com.ctrlaccess.a20220905_girumlemu_nycschools.api.SchoolsApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


/*        lifecycleScope.launch {
            SchoolsApi.getInstance().getSatResult("21K728").let {
                Log.d("TAG", "MainActivity: ${it.size}")
                it.forEach { satResult ->
                    Log.d("TAG", "MainActivity: ${satResult}")

                }
            }
        }*/


    }
}