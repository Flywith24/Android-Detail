package com.flywith24.detail

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.flywith24.baselib.ext.startActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Override
    fun start(v: View) {
        startActivity<ProcessActivity>()
    }
}