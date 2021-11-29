package com.flywith24.baselib.ext

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> Fragment.viewBinding() =
  FragmentViewBindingDelegate(T::class.java, this)

inline fun <reified T : ViewBinding> ComponentActivity.viewBinding() =
  ActivityViewBindingDelegate(T::class.java, this)

@Suppress("UNCHECKED_CAST")
class ActivityViewBindingDelegate<T : ViewBinding>(
  private val bindingClass: Class<T>,
  private val activity: ComponentActivity
) : ReadOnlyProperty<Activity, T> {
  private var _binding: T? = null

  init {
    activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
      override fun onCreate(owner: LifecycleOwner) {
        activity.setContentView(_binding!!.root)
      }
    })
  }

  override fun getValue(thisRef: Activity, property: KProperty<*>): T {
    if (_binding == null)
      _binding =
        bindingClass.getMethod("inflate", LayoutInflater::class.java)
          .invoke(null, thisRef.layoutInflater) as T
    return _binding!!
  }

}

class FragmentViewBindingDelegate<T : ViewBinding>(
  private val bindingClass: Class<T>,
  private val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {

  private var _binding: T? = null

  init {
    fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
      override fun onCreate(owner: LifecycleOwner) {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
          viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
              _binding = null
            }
          })
        }
      }
    })
  }

  override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
    if (_binding == null)
      _binding =
        bindingClass.getMethod("bind", View::class.java).invoke(null, thisRef.requireView()) as T
    return _binding!!
  }
}