package com.flywith24.detail

import com.flywith24.baselib.ext.*

/**
 * LaunchMode：singleTask
 * taskAffinity：default
 */
class LaunchModeC : CommonActivity() {
    override val customTitle: CharSequence by lazy {
        getString(R.string.customTitle).formatSpanned(
            javaClass.simpleName.color(getColor(R.color.colorPrimary)).bold().scale(2f),
            "singleTask".color(getColor(R.color.colorRed)).italic().scale(1.5f),
            packageName.color(getColor(R.color.colorAccent)).italic().scale(1.5f),
            "$taskId".color(getColor(R.color.colorPrimary)).italic().scale(1.5f),
            "$this"
        )
    }
}