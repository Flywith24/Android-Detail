package com.flywith24.detail

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import com.flywith24.baselib.ext.dp
import com.flywith24.baselib.ext.viewBinding
import com.flywith24.detail.databinding.ActivityWindowBinding
import com.google.android.material.snackbar.Snackbar


@SuppressLint("SetTextI18n")
class WindowActivity : AppCompatActivity(R.layout.activity_window) {

    private val binding by viewBinding<ActivityWindowBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.showPopupWindow.setOnClickListener { showPopupWindow(it) }
        binding.showDialog.setOnClickListener { showDialog(it) }
        binding.freeForm.setOnClickListener { freeForm(it) }
        binding.manageStatusBar.setOnClickListener { manageStatusBar(it) }
        binding.manageNavigationBar.setOnClickListener { manageNavigationBar(it) }
        binding.manageIME.setOnClickListener { manageIME(it) }
        binding.systemUiVisibility1.setOnClickListener { systemUiVisibility1(it) }
        binding.systemUiVisibility2.setOnClickListener { systemUiVisibility2(it) }
        binding.clearSystemUiVisibility.setOnClickListener { clearSystemUiVisibility(it) }
        binding.fitsSystemWindow.setOnClickListener { fitsSystemWindow(it) }
        Log.i("TAG", "onCreate: default ${window.decorView.systemUiVisibility}")
        /*强制设置 fitsSystemWindows 为 false*/
        /*(window.decorView as ViewGroup)[0].fitsSystemWindows = false
        (((window.decorView as ViewGroup)[0] as ViewGroup)[1] as ViewGroup)[0].fitsSystemWindows = false*/
        val containsFlag = 0x30200000.containsFlag(Intent.FLAG_ACTIVITY_NEW_TASK)
        Log.i("yyz11", "onCreate: $containsFlag")
    }

    /**
     * 显示 PopupWindow
     */
    private fun showPopupWindow(view: View) {
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
    private fun showDialog(view: View) {
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
    private fun freeForm(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
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
    }


    /**
     * WindowManager addView()
     */
    fun addView(view: View) {
        val manager = getSystemService(WINDOW_SERVICE) as WindowManager

        val button = Button(applicationContext).apply {
            text = "我可没有 Activity 哦"
            setBackgroundColor(Color.WHITE)
            setOnClickListener {
                manager.removeView(this)
            }
        }
        val layoutParams = WindowManager.LayoutParams().apply {
            //需要手动开启悬浮窗权限
            type =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                else WindowManager.LayoutParams.TYPE_PHONE

            format = PixelFormat.RGBA_8888
            width = Resources.getSystem().displayMetrics.widthPixels
            height = Resources.getSystem().displayMetrics.heightPixels
            x = 0
            y = 0
        }
        manager.addView(button, layoutParams)
    }

    private fun getWindowingModeMethodName(): String {
        return if (getCurrentApiVersion() >= 28.0f) "setLaunchWindowingMode" else "setLaunchStackId"
    }

    private fun getCurrentApiVersion(): Float =
        "${Build.VERSION.SDK_INT}.${Build.VERSION.PREVIEW_SDK_INT}".toFloat()

    private fun manageStatusBar(view: View) {
        ViewCompat.getWindowInsetsController(view)?.apply {
            val statusBar = WindowInsetsCompat.Type.statusBars()
            val isShow =
                ViewCompat.getRootWindowInsets(window.decorView)?.getInsets(statusBar)?.top != 0
            if (isShow) hide(statusBar) else show(statusBar)
            Snackbar.make(
                view,
                "${if (isShow) "hide" else "show"} statusBar",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun manageNavigationBar(view: View) {
        ViewCompat.getWindowInsetsController(view)?.apply {
            val navigationBar = WindowInsetsCompat.Type.navigationBars()
            val isShow = ViewCompat.getRootWindowInsets(window.decorView)
                ?.getInsets(navigationBar)?.bottom != 0
            if (isShow) hide(navigationBar) else show(navigationBar)
            Snackbar.make(
                view,
                "${if (isShow) "hide" else "show"} navigationBar",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun manageIME(view: View) {
        ViewCompat.getWindowInsetsController(view)?.apply {
            val ime = WindowInsetsCompat.Type.ime()
            val isShow =
                ViewCompat.getRootWindowInsets(window.decorView)?.getInsets(ime)?.bottom != 0
            if (isShow) hide(ime) else show(ime)
            Snackbar.make(view, "${if (isShow) "hide" else "show"} ime", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun systemUiVisibility1(view: View) {
        Log.i("TAG", "systemUiVisibility1: start ${window.decorView.systemUiVisibility}")
        binding.root.fitsSystemWindows = false
        val flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.decorView.systemUiVisibility = flag
        Snackbar.make(view, "add flag FULLSCREEN", Snackbar.LENGTH_SHORT).show()
        Log.i("TAG", "systemUiVisibility1: end ${window.decorView.systemUiVisibility}")
    }

    private fun systemUiVisibility2(view: View) {
        Log.i("TAG", "systemUiVisibility2: start ${window.decorView.systemUiVisibility}")
        binding.root.fitsSystemWindows = true
        val flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.decorView.systemUiVisibility = flag
        Snackbar.make(view, "add flag FULLSCREEN | STABLE", Snackbar.LENGTH_SHORT).show()
        Log.i("TAG", "systemUiVisibility2: end ${window.decorView.systemUiVisibility}")
    }

    private fun clearSystemUiVisibility(view: View) {
        binding.root.fitsSystemWindows = false
        binding.root.updatePadding(0, 0, 0, 0)
        window.decorView.systemUiVisibility = 0
        Snackbar.make(view, "clear all flags", Snackbar.LENGTH_SHORT).show()
    }

    private fun fitsSystemWindow(it: View) {
        startActivity(Intent(this, FitsSystemWindowActivity::class.java))
    }

    private fun Int.containsFlag(flag: Int) = this and flag != 0

    private fun Int.removeFlag(flag: Int) = this and flag.inv()
}