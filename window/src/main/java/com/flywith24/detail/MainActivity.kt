package com.flywith24.detail

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import com.flywith24.baselib.ext.dp


/**
 * @author Flywith24
 * @date   2020/9/1
 * time   9:12
 * description
 */
@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    /**
     * 显示 PopupWindow
     */
    fun showPopupWindow(view: View) {
        val button = Button(this).apply {
            text = "这是一个 PopupWindow"
            isAllCaps = false
            setPadding(10f.dp.toInt())
            setBackgroundColor(Color.RED)
        }
        val popupWindow = PopupWindow(
            button,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            isOutsideTouchable = true;
        }
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    /**
     * 显示 dialog
     */
    fun showDialog(view: View) {
        val button = Button(this).apply {
            text = "这是一个 Dialog"
            isAllCaps = false
            setPadding(10f.dp.toInt())
            setBackgroundColor(Color.RED)
        }
        Dialog(this).apply {
            setContentView(button)
        }.show()
    }

    /**
     * 1. 开启自由窗口：
     * adb shell settings put global enable_freeform_support 1
     * 2. 强制 Activity 可调整大小：
     * adb shell settings put global force_resizable_activities  1
     * 3. 重启设备
     *
     * 以自由窗口形式打开 app
     */
    fun freeForm(view: View) {
        try {
            val intent = Intent(this, FreeformActivity::class.java).apply {
                action = Intent.ACTION_MAIN
                addCategory(Intent.CATEGORY_LAUNCHER)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            val options = ActivityOptions.makeBasic()
            try {
                ActivityOptions::class.java.getMethod(
                    getWindowingModeMethodName(),
                    Int::class.javaPrimitiveType
                ).invoke(options, 5)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            startActivity(
                intent,
                options.setLaunchBounds(
                    Rect(
                        50,
                        50,
                        700,
                        700
                    )
                ).toBundle()
            )
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun getWindowingModeMethodName(): String {
        return if (getCurrentApiVersion() >= 28.0f) "setLaunchWindowingMode" else "setLaunchStackId"
    }

    private fun getCurrentApiVersion(): Float =
        "${Build.VERSION.SDK_INT}.${Build.VERSION.PREVIEW_SDK_INT}".toFloat()

    private var statusBar: Boolean = true
    fun manageStatusBar(view: View) {
        // Android 11 已弃用
        // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE

        window.decorView.windowInsetsController?.apply {
            statusBar = !statusBar
            if (statusBar) show(WindowInsets.Type.statusBars())
            else hide(WindowInsets.Type.statusBars())
        }
    }

    private var navigationBar: Boolean = true
    fun manageNavigationBar(view: View) {
        // Android 11 已弃用
        // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        window.decorView.windowInsetsController?.apply {
            navigationBar = !navigationBar
            if (navigationBar) show(WindowInsets.Type.navigationBars())
            else hide(WindowInsets.Type.navigationBars())
        }
    }
}