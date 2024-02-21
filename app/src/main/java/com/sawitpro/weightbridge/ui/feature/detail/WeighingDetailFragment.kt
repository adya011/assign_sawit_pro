package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.databinding.FragmentWeighingDetailBinding
import com.sawitpro.weightbridge.ui.feature.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeighingDetailFragment : BaseFragment() {

    private val viewModel by viewModel<WeighingDetailViewModel>()

    private val args by navArgs<WeighingDetailFragmentArgs>()

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
        viewModel.getDetail(args.detailId)
        setupView()
        setupObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupView() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(
                WeighingDetailFragmentDirections.actionOpenEdit(args.detailId)
            )
        }
    }

    private fun setupViews(data: WeighingTicketEntity) = with(binding) {
        tvId.text = data.uId
        tvDriverName.text = data.driverName
        tvLicenseNum.text = data.licenseNumber
        tvInbound.text = "Inbound: ${data.inboundWeight}"
        tvOutbound.text = "Outbound: ${data.outboundWeight}"
        tvNet.text = "Net: ${data.netWeight}"
    }

    private fun setupObserver() {
        viewModel.ticketDetailLiveData.observe(viewLifecycleOwner) {
            setupViews(it)
        }
    }
}