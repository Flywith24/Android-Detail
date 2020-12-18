package cn.flywith24.view_event

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @author yyz (杨云召)
 * @date   2020/12/18
 * time   14:55
 * description
 */
class CustomView1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        printDispatchTouchEvent(NAME, "递", false, event)
        val handled = super.dispatchTouchEvent(event)
        printDispatchTouchEvent(NAME, "归", handled, event)
        return handled
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val handled = Config.HANDLE_V1
        printTouchEvent(NAME, handled, event)
        if (event.action == MotionEvent.ACTION_UP) performClick()
        return handled
    }

    companion object {
        const val NAME = "CustomView1"
    }
}