package cn.flywith24.view_event

import android.util.Log
import android.view.MotionEvent

/**
 * @author yyz (杨云召)
 * @date   2020/12/18
 * time   14:52
 * description
 */

const val TAG = "yyz11"

val Int.name
    get() = when (this) {
        MotionEvent.ACTION_DOWN -> "ACTION_DOWN"
        MotionEvent.ACTION_MOVE -> "ACTION_MOVE"
        MotionEvent.ACTION_UP -> "ACTION_UP"
        MotionEvent.ACTION_CANCEL -> "ACTION_CANCEL"
        else -> "$this"
    }

fun printDispatchTouchEvent(name: String, type: String, handled: Boolean, event: MotionEvent) {
    Log.i(TAG, "$name dispatchTouchEvent $type: handled = $handled ${event.action.name}")
}

fun printTouchEvent(name: String, handled: Boolean, event: MotionEvent) {
    val result = if (handled) "" else "不"
    Log.w(TAG, "$name onTouchEvent: 我干了啊！结果$result OK ${event.action.name}")
}

fun printOnclick(name: String) {
    Log.e(TAG, "$name onClick 事件")
}