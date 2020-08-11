package com.flywith24.detail

import android.view.View
import com.flywith24.baselib.BaseActivity
import com.flywith24.baselib.ext.*

class MainActivity : BaseActivity(R.layout.activity_main) {
    override val customTitle: CharSequence by lazy {
        getString(R.string.customTitle).formatSpanned(
            javaClass.simpleName.color(getColor(R.color.colorPrimary)).bold().scale(2f),
            "standard".color(getColor(R.color.colorRed)).italic().scale(1.5f),
            "default".color(getColor(R.color.colorRed)).italic().scale(1.5f)
        )
    }

    /**
     * standard 启动
     */
    fun standardClick(view: View) = starActivity<MainActivity>()

    /**
     * singleTask 启动
     */
    fun jump2A(view: View) = starActivity<MainActivityA>()

    /**
     * singleInstance 启动
     */
    fun jump2B(view: View) = starActivity<MainActivityB>()

    /**
     * singleTask + 单独 taskAffinity 启动
     */
    fun jump2C(view: View) = starActivity<MainActivityC>()

    /**
     * singleInstance + 单独 taskAffinity 启动
     */
    fun jump2D(view: View) = starActivity<MainActivityD>()

}