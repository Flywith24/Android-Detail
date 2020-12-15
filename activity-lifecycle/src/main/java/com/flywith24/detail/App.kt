package com.flywith24.detail

import android.app.Application
import android.content.Context
import android.util.Log

/**
 * @author yyz (杨云召)
 * @date   2020/11/9
 * time   11:32
 * description
 */
class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        printMethod("Application attachBaseContext")
    }

    override fun onCreate() {
        super.onCreate()
        printMethod("Application onCreate")
    }

    companion object {
        private const val TAG = "yyz11"
        fun printMethod(name: String) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                Log.i(TAG, "$name: [${getProcessName()}] $this")
            }
        }
    }
}