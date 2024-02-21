package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sawitpro.weightbridge.databinding.FragmentWeighingCreateEditBinding
import com.sawitpro.weightbridge.ui.feature.base.BaseFragment

abstract class WeighingCreateEditFragment : BaseFragment() {

    private var _binding: FragmentWeighingCreateEditBinding? = null
    protected val binding get() = _binding!!

    protected var netWeight: Int = 0

    abstract fun getToolbarTitle(): String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeighingCreateEditBinding.inflate(inflater, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()

        // TODO: Make listener for net weight

        // TODO: Make date textbox
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupToolbar() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.tvToolbarTitle.text = getToolbarTitle()
    }
}