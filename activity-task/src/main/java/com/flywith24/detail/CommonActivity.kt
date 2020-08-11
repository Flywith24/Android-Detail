package com.flywith24.detail

import android.view.View
import com.flywith24.baselib.BaseActivity
import com.flywith24.baselib.ext.starActivity

/**
 * @author Flywith24
 * @date   2020/8/11
 * time   14:36
 * description
 */
abstract class CommonActivity : BaseActivity(R.layout.activity_main) {

    /**
     * 启动模式：standard
     */
    fun toLaunchModeA(view: View) = starActivity<LaunchModeA>()

    /**
     * 启动模式：singleTop
     */
    fun toLaunchModeB(view: View) = starActivity<LaunchModeB>()

    /**
     * 启动模式：singleTask
     */
    fun toLaunchModeC(view: View) = starActivity<LaunchModeC>()

    /**
     * 启动模式：singleInstance
     */
    fun toLaunchModeD(view: View) = starActivity<LaunchModeD>()

    /**
     * standard + 单独 taskAffinity 启动
     */
    fun toTLaunchModeA(view: View) = starActivity<TLaunchModeA>()

    /**
     * singleTop + 单独 taskAffinity 启动
     */
    fun toTLaunchModeB(view: View) = starActivity<TLaunchModeB>()

    /**
     * singleTask + 单独 taskAffinity 启动
     */
    fun toTLaunchModeC(view: View) = starActivity<TLaunchModeC>()

    /**
     * singleInstance + 单独 taskAffinity 启动
     */
    fun toTLaunchModeD(view: View) = starActivity<TLaunchModeD>()
}