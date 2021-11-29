package com.flywith24.detail

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import com.flywith24.baselib.ext.startActivity

class HomeActivity : Activity() {
    lateinit var mIntent: Intent

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        printLifecycle("onCreate")
        mIntent = Intent(this, RemoteService::class.java)
        startService(mIntent)

        window.decorView.viewTreeObserver.apply {
            registerFrameCommitCallback { Log.i(TAG, "registerFrameCommitCallback: ") }
            addOnPreDrawListener {
                Log.i(TAG, "addOnPreDrawListener: ")
                true
            }
            addOnDrawListener { Log.i(TAG, "addOnDrawListener: ") }
        }
    }

    fun click(view: View) = startActivity<ConfigActivity>()

    override fun onStart() {
        super.onStart()
        printLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        val button = Button(this)
        button.parent
        printLifecycle("onResume")
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        printLifecycle("onTopResumedActivityChanged")
    }
    
    override fun onPause() {
        super.onPause()
        printLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        printLifecycle("onStop")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        printLifecycle("onAttachedToWindow")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        printLifecycle("onWindowFocusChanged")
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(mIntent)
        printLifecycle("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        printLifecycle("onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        printLifecycle("onRestoreInstanceState")
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}
