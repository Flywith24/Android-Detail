package cn.flywith24.view_event

import android.annotation.SuppressLint
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
class CustomViewGroup2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        val intercepted = Config.INTERCEPT_VG2
        Log.d(TAG, "$NAME onInterceptTouchEvent intercepted = $intercepted ${event.action.name}")
        return intercepted
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        printDispatchTouchEvent(NAME, "递", false, event)
        val handled = super.dispatchTouchEvent(event)
        printDispatchTouchEvent(NAME, "归", handled, event)
        return handled
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) performClick()
        val handled = Config.HANDLE_VG2
        printTouchEvent(NAME, handled, event)
        return handled
    }

    companion object {
        private const val NAME = "CustomViewGroup2"
    }
}