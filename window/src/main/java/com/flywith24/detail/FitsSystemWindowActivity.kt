package com.flywith24.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flywith24.baselib.ext.adaptEdge2Edge

class FitsSystemWindowActivity : AppCompatActivity(R.layout.activity_fits_system_window) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.adaptEdge2Edge()
    }
}