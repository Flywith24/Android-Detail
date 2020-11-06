package com.flywith24.process_kill

import android.app.ActivityManager
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    fun killProcess(view: View) {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        manager.forceStopPackageAsUser("flywith24.android.detail", -1)
    }
}