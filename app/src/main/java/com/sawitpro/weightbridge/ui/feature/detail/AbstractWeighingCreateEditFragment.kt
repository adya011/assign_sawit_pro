package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.sawitpro.weightbridge.databinding.FragmentWeighingCreateEditBinding
import com.sawitpro.weightbridge.ui.feature.base.BaseFragment
import com.sawitpro.weightbridge.util.format
import com.sawitpro.weightbridge.util.formatDouble
import com.sawitpro.weightbridge.util.toDouble

abstract class AbstractWeighingCreateEditFragment : BaseFragment() {

    private var _binding: FragmentWeighingCreateEditBinding? = null
    protected val binding get() = _binding!!

    protected var netWeight: Double = 0.0

    abstract fun getToolbarTitle(): String
    abstract fun submitData()

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
        setupView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupView() = with(binding) {
        etInboundWeight.addTextChangedListener {
            val net = it.toDouble() - etOutboundWeight.text.toDouble()
            etNetWeight.setText(net.formatDouble())
            netWeight = net.format()
        }
        etOutboundWeight.addTextChangedListener {
            val net = etInboundWeight.text.toDouble() - it.toDouble()
            etNetWeight.setText(net.formatDouble())
            netWeight = net.format()
        }
        btnApply.setOnClickListener {
            if (etNetWeight.text.toDouble() >= 0) {
                submitData()
            } else {
                tilNetWeight.error = ERROR_NET_MUST_POSITIVE
            }
        }
    }

    private fun setupToolbar() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.tvToolbarTitle.text = getToolbarTitle()
    }

    companion object {
        const val ERROR_NET_MUST_POSITIVE = "Net Weight must be positive"
        const val NAV_RESULT_UPDATED = "updated"
    }
}