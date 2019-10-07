package com.example.submission_made.viewmodel

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SettingViewModel @Inject
constructor(var context: Context) : ViewModel() {

    val sharedPreferences = context.getSharedPreferences("save", MODE_PRIVATE)
    val editor = context.getSharedPreferences("save", MODE_PRIVATE).edit()

    fun getReleaseReminderCheck(): Boolean {
        return sharedPreferences.getBoolean("release_reminder", false)
    }

    fun getDailyReminderCheck(): Boolean {
        return sharedPreferences.getBoolean("daily_reminder", false)
    }

    fun setReleaseReminderCheck(isChecked: Boolean) {
        editor.putBoolean("release_reminder", isChecked)
        editor.apply()
    }

    fun setDailyReminderCheck(isChecked: Boolean) {
        editor.putBoolean("daily_reminder", isChecked)
        editor.apply()
    }
}