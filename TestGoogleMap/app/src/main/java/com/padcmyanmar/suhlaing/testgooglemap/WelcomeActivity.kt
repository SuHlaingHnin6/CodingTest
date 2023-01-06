package com.padcmyanmar.suhlaing.testgooglemap

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay

class WelcomeActivity : AppCompatActivity (){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed(Runnable{

                val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
                finish()

        },2500)
    }



}