package com.flywith24.baselib.ext

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.google.android.material.snackbar.Snackbar

inline fun <reified T> Activity.startActivity(
    requestCode: Int = -1,
    block: (intent: Intent) -> Unit = {}
) {
  val intent = Intent(this, T::class.java)
  block.invoke(intent)
  startActivityForResult(intent, requestCode)
}

val Float.dp
  get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

fun View.showSnackBar(text: String) {
  Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

fun printStackTrace() {
  val builder = StringBuilder()
  Thread.currentThread().stackTrace.forEach {
    builder.append(it.toString())
    builder.append("\n")
  }
  Log.v("yyz11", builder.toString())
}