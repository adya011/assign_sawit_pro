package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.databinding.FragmentWeighingDetailBinding
import com.sawitpro.weightbridge.ui.feature.base.BaseFragment
import com.sawitpro.weightbridge.util.formatDate
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

    private fun setupView() = with(binding) {
        ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupViews(data: WeighingTicketEntity) = with(binding) {
        tvId.text = data.uId
        tvDriverName.text = data.driverName
        tvLicenseNum.text = data.licenseNumber
        tvDate.text = data.date.formatDate()
        tvInbound.text = "Inbound: ${data.inboundWeight} kg"
        tvOutbound.text = "Outbound: ${data.outboundWeight} kg"
        tvNet.text = "Net: ${data.netWeight} kg"
    }

    private fun setupObserver() {
        viewModel.ticketDetailLiveData.observe(viewLifecycleOwner) {
            setupViews(it)
        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }
    }
}