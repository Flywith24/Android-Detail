package com.flywith24.detail.dispatch

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.flywith24.baselib.ext.adaptEdge2Edge

class DispatchWindowInsetsActivity : FragmentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.adaptEdge2Edge()
    val rootView = DispatchViewGroupLevel1(this).apply {
      layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
    setContentView(rootView)
  }
}