package com.flywith24.detail

import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ProcessActivity : AppCompatActivity(R.layout.activity_process) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text = getString(R.string.title, Process.myUid(), Process.myPid(), Process.myTid())
    }
}