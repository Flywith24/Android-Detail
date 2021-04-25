package com.flywith24.detail

import android.app.Activity
import android.util.Log

fun Activity.print(state: String) {
    Log.d("LifecycleTest", "$state: $this")
}

fun Activity.printLifecycle(state: String) {
    Log.i("LifecycleTest", "$state: $this")
}