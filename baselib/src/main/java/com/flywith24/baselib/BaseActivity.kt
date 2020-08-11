package com.flywith24.baselib

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

/**
 * @author Flywith24
 * @date   2020/8/11
 * time   12:01
 * description
 */
abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    abstract val title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<MaterialToolbar>(R.id.toolbar).title = title
    }
}