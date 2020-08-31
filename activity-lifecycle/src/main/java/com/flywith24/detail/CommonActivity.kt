package com.flywith24.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Flywith24
 * @date   2020/8/31
 * time   9:31
 * description
 */
abstract class CommonActivity : AppCompatActivity(R.layout.activity_common) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("onCreate")
    }

    override fun onStart() {
        super.onStart()
        print("onStart")
    }

    override fun onPause() {
        super.onPause()
        print("onPause")
    }

    override fun onResume() {
        super.onResume()
        print("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        print("onRestart")
    }

    override fun onStop() {
        super.onStop()
        print("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        print("onDestroy")
    }
}