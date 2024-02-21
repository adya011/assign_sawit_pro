package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sawitpro.weightbridge.databinding.FragmentWeighingDetailBinding

class WeighingDetailFragment : Fragment() {

    private var _binding: FragmentWeighingDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeighingDetailBinding.inflate(inflater, container, false)
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

    private fun setupToolbar() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupView() = with(binding) {
        /*val id = args.detailId
        val driverName = args.driverName
        val licenseNum = args.licenseNum
        val inboundWeight = args.inboundWeight
        val outboundWeight = args.outboundWeight
        val netWeight = args.netWeight

        tvId.text = id
        tvDriverName.text = driverName
        tvLicenseNum.text = licenseNum
        tvInbound.text = "Inbound: $inboundWeight"
        tvOutbound.text = "Outbound: $outboundWeight"
        tvNet.text = "Net: $netWeight"*/
    }
}