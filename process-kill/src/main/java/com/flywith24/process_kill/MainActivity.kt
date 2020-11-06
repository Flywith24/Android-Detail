package com.flywith24.process_kill

import android.app.ActivityManager
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    /**
     * 安装 multi-process.apk
     */
    fun forceStopMultiProcess(view: View) {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        manager.forceStopPackageAsUser("flywith24.detail.process", -1)
    }

    /**
     * 安装 shareA.apk
     */
    fun forceStopMultiShareUserIdA(view: View) {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        manager.forceStopPackageAsUser("flywith24.detail.shareA")
    }

    /**
     * 安装 shareB.apk
     */
    fun forceStopMultiShareUserIdB(view: View) {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        manager.forceStopPackageAsUser("flywith24.detail.shareB")
    }
}