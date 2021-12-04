package com.flywith24.detail.fitssystemwindow

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flywith24.baselib.ext.adaptEdge2Edge
import com.flywith24.baselib.ext.isLightStatusBar
import com.flywith24.baselib.ext.viewBinding
import com.flywith24.detail.R
import com.flywith24.detail.databinding.ActivityRecyclerViewBinding

/**
 * fitsSystemWindows = true 与 clipToPadding = false 的组合效果
 */
class RecyclerViewActivity : FragmentActivity() {

  private val binding by viewBinding<ActivityRecyclerViewBinding>()
  private val simpleAdapter by lazy { SimpleAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.adaptEdge2Edge()
    window.isLightStatusBar = true
    val list = ArrayList<String>()
    for (i in 1..20) {
      list.add(i.toString())
    }
    binding.root.fitsSystemWindows = true
    binding.root.clipToPadding = false
    binding.root.adapter = simpleAdapter
    simpleAdapter.submitList(list)
  }

  class SimpleAdapter() : ListAdapter<String, SimpleViewHolder>(object : DiffUtil.ItemCallback<String?>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem === newItem


    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
  }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
      val itemView = AppCompatTextView(parent.context).also {
        it.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        it.textSize = 40f
        it.gravity = Gravity.CENTER
        it.setBackgroundColor(ContextCompat.getColor(parent.context, R.color.colorAccent))
      }
      return SimpleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
      holder.bind(position)
    }


  }

  class SimpleViewHolder(private val rootView: AppCompatTextView) : RecyclerView.ViewHolder(rootView) {

    fun bind(position: Int) {
      rootView.text = position.toString()
    }

  }
}