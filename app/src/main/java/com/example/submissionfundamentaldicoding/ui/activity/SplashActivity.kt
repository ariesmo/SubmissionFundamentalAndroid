package com.example.submissionfundamentaldicoding.ui.activity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submissionfundamentaldicoding.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val restID = resources.getIdentifier("bright", "raw", packageName)
        val mediaPlayer = MediaPlayer.create(this, restID)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}