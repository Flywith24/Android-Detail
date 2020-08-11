package com.flywith24.detail

import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    fun standardClick(view: View) = starActivity<MainActivity>()

    fun singleTaskClick(view: View) = starActivity<MainActivityA>()

    fun singleInstanceClick(view: View) = starActivity<MainActivityB>()

}