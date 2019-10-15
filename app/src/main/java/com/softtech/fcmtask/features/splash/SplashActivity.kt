package com.softtech.fcmtask.features.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softtech.fcmtask.R
import com.softtech.fcmtask.features.home.activities.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moveToHome()
    }

    fun moveToHome(){
        val  intent= Intent(this, HomeActivity::class.java)
     overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
       finish()
    }
}
