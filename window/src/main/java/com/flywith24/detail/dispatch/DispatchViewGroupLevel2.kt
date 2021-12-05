package com.flywith24.detail.dispatch

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import com.flywith24.baselib.CustomLayout
import com.flywith24.baselib.TAG
import com.flywith24.detail.R

class DispatchViewGroupLevel2 @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : CustomLayout(context, attrs) {

  private val child1 by lazy { DispatchChildViewLevel3(context).also { it.tag = "child1" } }
  private val child2 by lazy { DispatchChildViewLevel3(context).also { it.tag = "child2" } }

  init {
    setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
    addView(child1, width = 10.dp, height = 10.dp)
    addView(child2, width = 10.dp, height = 10.dp)
  }

  override fun onMeasureChildren(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    forEachAutoMeasure()
    setMeasuredDimension(measuredWidth, measuredHeight)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    child1.let { it.layout(paddingLeft, paddingTop) }
    child2.let { it.layout(right - it.measuredWidth - paddingRight, paddingTop) }
  }

  override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
    val tag = "parent: ${(parent as ViewGroup).tag} this: $tag"
    Log.d(TAG, "DispatchViewGroupLevel2 onApplyWindowInsets: start ${insets.isConsumed} $tag")
    val result = super.onApplyWindowInsets(insets)

    Log.d(TAG, "DispatchViewGroupLevel2 onApplyWindowInsets: end ${result.isConsumed} $tag")
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) WindowInsets.CONSUMED
    else insets.consumeStableInsets()
  }
}