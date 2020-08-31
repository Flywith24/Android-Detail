package com.flywith24.detail

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.flywith24.baselib.ext.startActivity

/**
 * @author Flywith24
 * @date   2020/8/31
 * time   9:31
 * description
 */
abstract class CommonActivity : AppCompatActivity(R.layout.activity_common) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printLifecycle("onCreate")
    }

    override fun onStart() {
        super.onStart()
        printLifecycle("onStart")
    }

    override fun onPause() {
        super.onPause()
        printLifecycle("onPause")
    }

    override fun onResume() {
        super.onResume()
        printLifecycle("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        printLifecycle("onRestart")
    }

    override fun onStop() {
        super.onStop()
        printLifecycle("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        printLifecycle("onDestroy")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        print("onConfigurationChanged")
    }

    override fun onWindowAttributesChanged(params: WindowManager.LayoutParams?) {
        super.onWindowAttributesChanged(params)
        print("onWindowAttributesChanged")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        print("onAttachedToWindow")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        print("onWindowFocusChanged $hasFocus")
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean, newConfig: Configuration?) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig)
        print("onMultiWindowModeChanged $isInMultiWindowMode")
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        print("onPictureInPictureModeChanged $isInPictureInPictureMode")
    }

    fun click(view: View) = startActivity<ConfigActivity>()
}