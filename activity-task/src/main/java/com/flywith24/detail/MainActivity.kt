package com.flywith24.detail

import android.view.View

class MainActivity : BaseActivity(R.layout.activity_main) {
    override val title: String = "MainActivity LaunchMode:standard"

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