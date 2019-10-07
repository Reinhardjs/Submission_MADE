package com.example.submission_made.ui.activity

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.ToggleButton
import androidx.appcompat.widget.Toolbar
import com.example.submission_made.R
import com.example.submission_made.databinding.ActivitySettingBinding
import com.example.submission_made.ui.base.BaseActivity
import com.example.submission_made.utils.AlarmReceiver
import com.example.submission_made.viewmodel.SettingViewModel

class SettingActivity : BaseActivity<SettingViewModel, ActivitySettingBinding>(), CompoundButton.OnCheckedChangeListener {

    lateinit var toolbarTop: Toolbar
    lateinit var toggleRelease: ToggleButton
    lateinit var toggleDaily: ToggleButton

    lateinit var alarmReceiver: AlarmReceiver

    override val layoutRes: Int = R.layout.activity_setting

    override fun getViewModel(): Class<SettingViewModel> {
        return SettingViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbarTop = dataBinding.toolbar
        toolbarTop.title = getString(R.string.reminder_toolbar_title)
        setSupportActionBar(toolbarTop)

        toggleRelease = dataBinding.toggleRelease
        toggleDaily = dataBinding.toggleDaily

        alarmReceiver = AlarmReceiver()
        alarmReceiver.setRepeatingAlarmRelease(applicationContext, true)

        if (viewModel.getReleaseReminderCheck()){
            toggleRelease.toggle()
        }

        if (viewModel.getDailyReminderCheck()){
            toggleDaily.toggle()
        }

        toggleRelease.setOnCheckedChangeListener(this)
        toggleDaily.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (buttonView == toggleRelease){

            alarmReceiver.setRepeatingAlarmRelease(applicationContext, isChecked)
            viewModel.setReleaseReminderCheck(isChecked)

        } else if (buttonView == toggleDaily){

            alarmReceiver.setRepeatingAlarmDaily(applicationContext, isChecked)
            viewModel.setDailyReminderCheck(isChecked)

        }
    }

}
