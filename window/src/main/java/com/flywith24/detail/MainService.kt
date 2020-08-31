package com.flywith24.detail

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.WindowManager
import android.widget.Button

/**
 * @author Flywith24
 * @date   2020/8/31
 * time   11:41
 * description
 * 证明不需要 Activity 也能显示界面
 * 使用方法：
 * 1. 安装根目录 window.apk
 * 2. 为该 app 开启悬浮窗权限
 * 3. adb 执行该命令 adb shell am startservice -n flywith24.android.detail.window/com.flywith24.detail.MainService
 */
class MainService : Service() {
    @SuppressLint("SetTextI18n")
    override fun onCreate() {
        super.onCreate()
        val manager = getSystemService(WINDOW_SERVICE) as WindowManager

        val button = Button(applicationContext).apply {
            text = "我可没有 Activity 哦"
            setBackgroundColor(Color.WHITE)
            setOnClickListener {
                manager.removeView(this)
                stopSelf()
            }
        }
        val layoutParams = WindowManager.LayoutParams().apply {
            type =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                else WindowManager.LayoutParams.TYPE_PHONE

            format = PixelFormat.RGBA_8888
            width = Resources.getSystem().displayMetrics.widthPixels
            height = Resources.getSystem().displayMetrics.heightPixels
            x = 0
            y = 0
        }
        manager.addView(button, layoutParams)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}