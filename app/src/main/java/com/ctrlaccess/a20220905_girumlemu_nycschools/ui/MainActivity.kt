package com.ctrlaccess.a20220905_girumlemu_nycschools.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ctrlaccess.a20220905_girumlemu_nycschools.R

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