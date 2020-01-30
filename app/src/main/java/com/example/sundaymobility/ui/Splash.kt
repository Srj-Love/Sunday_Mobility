package com.example.sundaymobility.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sundaymobility.R

class Splash : AppCompatActivity() {

    private val SPLASH_TIMEING: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()

    }

    private fun init() {
        Handler().postDelayed({
            startActivity(Intent(this@Splash, MainActivity::class.java))
            finish()
        }, SPLASH_TIMEING)
    }
}
