package com.flywith24.detail

import android.os.Process
import com.flywith24.baselib.ext.*

/**
 * LaunchMode：standard
 * taskAffinity：default
 */
class LaunchModeAProcess1 : CommonActivity() {
    override val customTitle: CharSequence by lazy {
        getString(R.string.customTitle).formatSpanned(
            javaClass.simpleName.color(getColor(R.color.colorPrimary)).bold().scale(2f),
            "standard".color(getColor(R.color.colorRed)).italic().scale(1.5f),
            packageName.color(getColor(R.color.colorAccent)).italic().scale(1.5f),
            "$taskId ${Process.myPid()}".color(getColor(R.color.colorPrimary)).italic().scale(1.5f),
            "$this"
        )
    }
}