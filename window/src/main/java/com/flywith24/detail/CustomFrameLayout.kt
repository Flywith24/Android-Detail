package com.flywith24.detail

import android.content.Context
import android.util.AttributeSet
import android.view.WindowInsets
import androidx.core.widget.NestedScrollView
import com.flywith24.baselib.ext.printStackTrace

class CustomFrameLayout @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : NestedScrollView(context, attrs) {
  override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
    super.setPadding(left, top, right, bottom)
    printStackTrace()
  }

  override fun setFitsSystemWindows(fitSystemWindows: Boolean) {
    super.setFitsSystemWindows(fitSystemWindows)
    printStackTrace()
  }

  override fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets {
    // custom apply WindowInsets
    return super.onApplyWindowInsets(insets)
  }
}