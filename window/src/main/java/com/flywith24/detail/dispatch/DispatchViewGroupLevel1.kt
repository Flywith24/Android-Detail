package com.flywith24.detail.dispatch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.WindowInsets
import com.flywith24.baselib.CustomLayout
import com.flywith24.baselib.TAG
import com.flywith24.baselib.matchParent

class DispatchViewGroupLevel1 @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : CustomLayout(context, attrs) {

  private val child1 by lazy { DispatchViewGroupLevel2(context).also { it.tag = "child1" } }
  private val child2 by lazy { DispatchViewGroupLevel2(context).also { it.tag = "child2" } }

  init {
    addView(child = child1, width = matchParent, height = 50.dp)
    addView(child = child2, width = matchParent, height = 100.dp)
  }


  override fun onMeasureChildren(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    forEachAutoMeasure()
    setMeasuredDimension(measuredWidth, measuredHeight)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    child1.let { it.layout(0, 0) }
    child2.let { it.layout(0, bottom - child2.measuredHeight) }
  }

  override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
    Log.i(TAG, "DispatchViewGroupLevel1 onApplyWindowInsets: start ${insets.isConsumed} $tag")
    val result = super.onApplyWindowInsets(insets)
    Log.i(TAG, "DispatchViewGroupLevel1 onApplyWindowInsets: end ${result.isConsumed} $tag")
    return result
  }
}