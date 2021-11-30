package com.flywith24.detail.fitssystemwindow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatusBarViewModel : ViewModel() {
    private val _lightStatusBar: MutableLiveData<Boolean> = MutableLiveData(false)
    val lightStatusBar: LiveData<Boolean> = _lightStatusBar

    fun setLightStatusBar(isLight: Boolean) {
        _lightStatusBar.value = isLight
    }
}