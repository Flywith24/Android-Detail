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

fun printEvent(name: String, functionName: String, handled: Boolean, event: MotionEvent) {
    Log.i(TAG, "$name $functionName: $handled ${event.action.name}")
}