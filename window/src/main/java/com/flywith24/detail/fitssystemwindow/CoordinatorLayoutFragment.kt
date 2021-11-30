package com.flywith24.detail.fitssystemwindow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.flywith24.baselib.ext.isLightStatusBar
import com.flywith24.baselib.ext.viewBinding
import com.flywith24.detail.R
import com.flywith24.detail.databinding.FragmentCoordinatorLayoutBinding

class CoordinatorLayoutFragment : Fragment(R.layout.fragment_coordinator_layout) {
    private val binding by viewBinding<FragmentCoordinatorLayoutBinding>()
    private val viewModel by viewModels<StatusBarViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setLightStatusBar(false)
        viewModel.lightStatusBar.observe(viewLifecycleOwner) {
            requireActivity().window.isLightStatusBar = it
        }
    }
}