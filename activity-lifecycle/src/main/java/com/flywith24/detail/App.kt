package com.flywith24.detail

import android.app.Application
import android.content.Context
import android.util.Log

class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        printMethod("Application attachBaseContext", this)
    }

    override fun onCreate() {
        super.onCreate()
        printMethod("Application onCreate", this)
        printStackTrace()
    }

    override fun onTerminate() {
        super.onTerminate()
        printMethod("Application onTerminate",this)
    }

    companion object {
        private const val TAG = "yyz11"
        fun printMethod(name: String, o: Any = this) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                Log.i(TAG, "$name: [${getProcessName()}] $o")
            }
        }
    }
}