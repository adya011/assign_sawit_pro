package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.util.setGone
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeighingCreateFragment : WeighingCreateEditFragment() {

    private val viewModel by viewModel<WeighingCreateViewModel>()

    override fun getToolbarTitle(): String = "Create Weighing Detail"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupView()
    }

    private fun setupView() = with(binding) {
        tvLabelId.setGone()
        tvValueId.setGone()
        btnApply.setOnClickListener {
            viewModel.createWeighingDetail(
                RequestCreateEditWeighingTicketEntity(
                    driverName = etDriverName.text.toString(),
                    licenseNumber = etLicenseNum.text.toString(),
                    date = etDate.text.toString(),
                    inboundWeight = etInboundWeight.text.toString().toInt(),
                    outboundWeight = etOutboundWeight.text.toString().toInt(),
                    netWeight =
                    etInboundWeight.text.toString().toInt() - etOutboundWeight.text.toString().toInt()
                )
            )
        }
    }

    private fun setupObserver() {
        viewModel.setWeighingDetailLiveData.observe(viewLifecycleOwner) {
            showToast("Success Create Ticket")
            findNavController().navigateUp()
        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }

        //TODO: loading state
    }
}