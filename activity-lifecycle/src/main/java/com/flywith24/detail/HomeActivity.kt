package com.flywith24.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.flywith24.baselib.ext.startActivity

class HomeActivity : Activity() {
    lateinit var mIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        printStackTrace()
        printInfo("onCreate")
        mIntent = Intent(this, RemoteService::class.java)
        startService(mIntent)
    }

    fun click(view: View) = startActivity<ConfigActivity>()

    private fun printStackTrace() {
        val builder = StringBuilder()
        Thread.currentThread().stackTrace.forEach {
            builder.append(it.toString())
            builder.append("\n")
        }
        Log.v(TAG, builder.toString())
    }

    override fun onStart() {
        super.onStart()
        printInfo("onStart")
        printStackTrace()
    }

    override fun onResume() {
        super.onResume()
        val button = Button(this)
        button.parent
        printInfo("onResume")
        printStackTrace()

    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(mIntent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState: $this")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState: $this")
    }

    private fun printInfo(tag: String) {
        Log.v(
            TAG,
            "$tag: decorView parent ${window.decorView.parent} width :${ll.width} height :${ll.height}"
        )
    }

    companion object {
        private const val TAG = "yyz11"
    }
}
