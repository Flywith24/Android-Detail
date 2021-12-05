package com.flywith24.detail.dispatch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.core.content.ContextCompat
import com.flywith24.baselib.TAG
import com.flywith24.detail.R

class DispatchChildViewLevel3 @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

  init {
    setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
  }

  override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
    val tag = "parent: ${(parent as ViewGroup).tag} this: $tag"
    Log.v(TAG, "DispatchChildViewLevel3 onApplyWindowInsets: start $tag")
    val result = super.onApplyWindowInsets(insets)
    Log.v(TAG, "DispatchChildViewLevel3 onApplyWindowInsets: end ${result.isConsumed} $tag")
    return result
  }
}