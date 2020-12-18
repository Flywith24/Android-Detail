package cn.flywith24.view_event

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.flywith24.baselib.ext.printStackTrace

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 一个事件序列只打印一次堆栈
        if (event.action == MotionEvent.ACTION_DOWN) printStackTrace()
        val handled = super.onTouchEvent(event)

        Log.i(TAG, "onTouchEvent: $handled ${event.action.name}")

        return handled
    }
}