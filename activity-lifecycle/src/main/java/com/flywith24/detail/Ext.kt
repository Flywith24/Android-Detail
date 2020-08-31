package com.flywith24.detail

import android.app.Activity
import android.util.Log

/**
 * @author Flywith24
 * @date   2020/8/31
 * time   9:28
 * description
 */
fun Activity.print(state: String) {
    Log.i("LifecycleTest", "$state: $this")
}