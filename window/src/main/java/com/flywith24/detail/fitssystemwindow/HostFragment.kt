package com.flywith24.detail.fitssystemwindow

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.flywith24.baselib.ext.isLightStatusBar
import com.flywith24.baselib.ext.viewBinding
import com.flywith24.detail.R
import com.flywith24.detail.databinding.FragmentHostBinding

class HostFragment : Fragment(R.layout.fragment_host) {
    private val binding by viewBinding<FragmentHostBinding>()
    private val viewModel by viewModels<StatusBarViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.group) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }
            WindowInsetsCompat.CONSUMED
        }
        binding.coordinatorLayout.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace<CoordinatorLayoutFragment>(R.id.container)
            }
        }
        viewModel.setLightStatusBar(true)
        viewModel.lightStatusBar.observe(viewLifecycleOwner) {
            requireActivity().window.isLightStatusBar = it
        }
    }
}