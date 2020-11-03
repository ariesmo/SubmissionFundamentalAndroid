package com.example.submissionfundamentaldicoding.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.receiver.AlarmReceiver
import com.example.submissionfundamentaldicoding.ui.fragment.SettingsFragment

class SettingsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SettingsFragment())
            .commit()

    }
}