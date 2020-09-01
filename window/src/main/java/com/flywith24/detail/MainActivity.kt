package com.flywith24.detail

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
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
}