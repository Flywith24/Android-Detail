package cn.flywith24.view_event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 * @author yyz (杨云召)
 * @date   2020/12/18
 * time   14:50
 * description
 */
class CustomViewGroup2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        val handled = if (Config.INTERCEPT_VG2) true else super.onInterceptTouchEvent(event)
        printEvent("CustomViewGroup2", "onInterceptTouchEvent", handled, event)
        return handled
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val handled = if (Config.HANDLE_VG2) true else super.dispatchTouchEvent(event)
        printEvent("CustomViewGroup2", "dispatchTouchEvent", handled, event)
        return handled
    }
}