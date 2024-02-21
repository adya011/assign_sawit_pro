package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeighingEditFragment : WeighingCreateEditFragment() {

    private val viewModel by viewModel<WeighingEditViewModel>()

    private val args by navArgs<WeighingEditFragmentArgs>()

    override fun getToolbarTitle(): String = "Edit Weighing Detail"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.getDetail(args.detailId)
    }

    private fun setupViews(data: WeighingTicketEntity) = with(binding) {
        tvValueId.text = data.uId
        etDriverName.setText(data.driverName)
        etLicenseNum.setText(data.licenseNumber)
        etDate.setText(data.date)
        etInboundWeight.setText(data.inboundWeight.toString())
        etOutboundWeight.setText(data.outboundWeight.toString())
        etNetWeight.setText(netWeight.toString())

        btnApply.setOnClickListener {
            viewModel.updateWeighingDetail(
                uId = data.uId,
                request = RequestCreateEditWeighingTicketEntity(
                    driverName = etDriverName.text.toString(),
                    licenseNumber = etLicenseNum.text.toString(),
                    date = etDate.text.toString(),
                    inboundWeight = etInboundWeight.text.toString().toInt(),
                    outboundWeight = etOutboundWeight.text.toString().toInt(),
                    netWeight = netWeight
                )
            )
        }
    }

    private fun setupObserver() {
        viewModel.ticketDetailLiveData.observe(viewLifecycleOwner) {
            setupViews(it)
        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }

        //TODO: loading state
    }
}