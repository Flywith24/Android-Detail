package com.flywith24.baselib.ext

import android.app.Activity
import android.content.Intent

/**
 * @author Flywith24
 * @date   2020/8/11
 * time   10:42
 * description
 */
inline fun <reified T> Activity.starActivity(
    block: (intent: Intent) -> Unit = {},
    requestCode: Int = -1
) {
    val intent = Intent(this, T::class.java)
    block.invoke(intent)
    startActivityForResult(intent, requestCode)
}