package com.example.submissionfundamentaldicoding.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.receiver.AlarmReceiver

class SettingsFragment : PreferenceFragmentCompat() {

    private val TAG = SettingsFragment::class.java.simpleName
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        alarmReceiver = AlarmReceiver()

        loadSettings()
    }

    private fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        val reminder = sp.getBoolean("notifications", false)
        Log.d(TAG, reminder.toString())

        val repeatTime = "09:00"
        val repeatMessage = "Daily Reminder"

        if (reminder == true){
            alarmReceiver.setRepeatingAlarm(requireActivity(), AlarmReceiver.TYPE_REPEATING, repeatTime, repeatMessage)
        }
    }
}