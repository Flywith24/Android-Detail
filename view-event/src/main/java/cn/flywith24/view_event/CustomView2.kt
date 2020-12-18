package cn.flywith24.view_event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author yyz (杨云召)
 * @date   2020/12/18
 * time   14:55
 * description
 */
class CustomView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        printEvent(NAME, "递", false, event)
        val handled = if (Config.HANDLE_V2) true else super.dispatchTouchEvent(event)
        printEvent(NAME, "归", handled, event)
        return handled
    }

    companion object {
        private const val NAME = "CustomView2"
    }
}