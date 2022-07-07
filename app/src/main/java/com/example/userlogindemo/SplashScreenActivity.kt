package com.example.userlogindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import dagger.hilt.android.AndroidEntryPoint


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val actionBar = supportActionBar
        actionBar!!.hide()
        actionBar.setDisplayHomeAsUpEnabled(true)
/*
        if (SharePref.getBooleanValue(this, "isLogin")) {
            Log.d("TAG","isLogin")
            //Handler().postDelayed({
                val i = Intent(this,MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                startActivity(i);
                finish()
                                  //},3000)

        } else {
            Log.d("TAG","isFirstTimeRun")
            if (!SharePref.getBooleanValue(this, "isFirstTimeRun")) {
           //     Handler().postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
             //   }, 3000)
            }
        }*/


        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
       // 3000 is the delayed time in milliseconds.
    }
    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
        if(SharePref.getBooleanValue(this,"isLogin")){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }else{
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        }, 3000)
    }
}
