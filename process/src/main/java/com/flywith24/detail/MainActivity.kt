package com.flywith24.detail

import android.os.Bundle
import android.os.Process
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.flywith24.baselib.ext.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text = getString(R.string.title, Process.myUid(), Process.myPid(), Process.myTid())
        printId()
        val system = ThreadGroup("custom").parent.parent
        system.list()
    }

    /**
     * 打开在单独进程的 Activity
     */
    fun start(v: View) = startActivity<ProcessActivity>()

    /**
     * 子线程中打印 tid
     */
    fun startThread(v: View) =
        Thread() {
            Log.i(TAG, "startThread: ${Thread.currentThread().name} ${Thread.currentThread().id}")
            val myTid = Process.myTid()
            val name = Thread.currentThread().name
            val threadId = Thread.currentThread().id

            runOnUiThread {
                tvThread.text = getString(R.string.thread, myTid, name, threadId)
            }

            SystemClock.sleep(10000)
            printId()
        }.start()


    /**
     * 调用 [Process.killProcess] 「自杀」
     */
    fun kill(view: View) {
        Process.killProcess(Process.myPid())
    }

    private fun printId() {
        Log.i(TAG, "uid：${Process.myUid()} pid : ${Process.myPid()} tid：${Process.myTid()}")
    }

    companion object {
        private const val TAG = "yyz11"
    }
}