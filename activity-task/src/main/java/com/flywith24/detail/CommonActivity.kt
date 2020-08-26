package com.flywith24.detail

import android.content.Intent
import android.view.View
import com.flywith24.baselib.BaseActivity
import com.flywith24.baselib.ext.startActivity

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
    fun toLaunchModeA(view: View) = startActivity<LaunchModeA>()

    /**
     * 启动模式：singleTop
     */
    fun toLaunchModeB(view: View) = startActivity<LaunchModeB>()

    /**
     * 启动模式：singleTask
     * 如果测试 startActivityForResult 参数传入 requestCode ，值 >=0
     */
    fun toLaunchModeC(view: View) = startActivity<LaunchModeC>(/*requestCode = 1*/)


    /**
     * 启动模式：singleInstance
     * 如果测试 startActivityForResult 参数传入 requestCode ，值 >=0
     */
    fun toLaunchModeD(view: View) = startActivity<LaunchModeD>(/*requestCode = 1*/)

    /**
     * standard + 单独 taskAffinity 启动
     */
    fun toTLaunchModeA(view: View) = startActivity<TLaunchModeA>()

    /**
     * singleTop + 单独 taskAffinity 启动
     */
    fun toTLaunchModeB(view: View) = startActivity<TLaunchModeB>()

    /**
     * singleTask + 单独 taskAffinity 启动
     * 如果测试 startActivityForResult 参数传入 requestCode ，值 >=0，把注释代码打开即可
     */
    fun toTLaunchModeC(view: View) = startActivity<TLaunchModeC>(/*requestCode = 1*/)

    /**
     * singleInstance + 单独 taskAffinity 启动
     * 如果测试 startActivityForResult 参数传入 requestCode ，值 >=0
     */
    fun toTLaunchModeD(view: View) = startActivity<TLaunchModeD>(/*requestCode = 1*/)

    /**
     * 使用 FLAG_ACTIVITY_NEW_TASK 启动
     */
    fun toFlagA(view: View) = startActivity<FlagA>() {
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}