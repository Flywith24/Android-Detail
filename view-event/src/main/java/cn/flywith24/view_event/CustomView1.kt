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
        // 如果 Config.INTERCEPT_V1 为 true 且是一个按下动作，则子节点拦截
        if (Config.INTERCEPT_V1 && event.action == MotionEvent.ACTION_DOWN) {
            parent.requestDisallowInterceptTouchEvent(true)
            Log.w(TAG, "CustomView1 子节点禁止父节点拦截 ACTION_DOWN", )
        }
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