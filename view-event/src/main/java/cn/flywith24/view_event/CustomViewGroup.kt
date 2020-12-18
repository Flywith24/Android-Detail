package cn.flywith24.view_event

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 * @author yyz (杨云召)
 * @date   2020/12/18
 * time   14:50
 * description
 */
class CustomViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    override fun onInterceptHoverEvent(event: MotionEvent): Boolean {
        val handled = super.onInterceptHoverEvent(event)
        printEvent("CustomViewGroup", "onInterceptHoverEvent", handled, event)
        return handled
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val handled = super.dispatchTouchEvent(event)
        printEvent("CustomViewGroup", "dispatchTouchEvent", handled, event)
        return handled
    }
}