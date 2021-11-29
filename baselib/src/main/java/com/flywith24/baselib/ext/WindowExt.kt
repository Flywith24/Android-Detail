package com.flywith24.baselib.ext

import android.graphics.Color
import android.os.Build
import android.view.Window
import androidx.core.view.WindowCompat

/**
 * 适配 edge-to-edge
 */
fun Window.adaptEdge2Edge() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.statusBarColor = Color.TRANSPARENT
        this.navigationBarColor = Color.TRANSPARENT
    }
}