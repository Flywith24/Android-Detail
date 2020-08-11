package com.flywith24.detail

import android.app.Activity
import android.content.Intent

/**
 * @author Flywith24
 * @date   2020/8/11
 * time   10:42
 * description
 */
inline fun <reified T> Activity.starActivity(block: (intent: Intent) -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block.invoke(intent)
    startActivity(intent)
}