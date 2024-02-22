package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.util.getCurrentDate
import com.sawitpro.weightbridge.util.toDouble
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeighingEditFragment : AbstractWeighingCreateEditFragment() {

    private val viewModel by viewModel<WeighingEditViewModel>()

    private val args by navArgs<WeighingEditFragmentArgs>()

    override fun getToolbarTitle(): String = TITLE_EDIT_TICKET

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.getDetail(args.detailId)
    }

    override fun submitData() = with(binding) {
        viewModel.updateWeighingDetail(
            uId = tvValueId.text.toString(),
            request = RequestCreateEditWeighingTicketEntity(
                driverName = etDriverName.text.toString(),
                licenseNumber = etLicenseNum.text.toString(),
                date = getCurrentDate(),
                inboundWeight = etInboundWeight.text.toDouble(),
                outboundWeight = etOutboundWeight.text.toDouble()
            )
        )
    }

    private fun setupViews(data: WeighingTicketEntity) = with(binding) {
        tvValueId.text = data.uId
        etDriverName.setText(data.driverName)
        etLicenseNum.setText(data.licenseNumber)
        etInboundWeight.setText(data.inboundWeight.toString())
        etOutboundWeight.setText(data.outboundWeight.toString())
        etNetWeight.setText(netWeight.toString())
        btnApply.isEnabled = true
    }

    private fun setupObserver() {
        viewModel.ticketDetailLiveData.observe(viewLifecycleOwner) {
            setupViews(it)
        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }

        viewModel.updateWeighingDetailLiveData.observe(viewLifecycleOwner) {
            showToast(SUCCESS_UPDATE_TICKET)
            findNavController().previousBackStackEntry?.savedStateHandle?.set(NAV_RESULT_UPDATED, true)
            findNavController().popBackStack()
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) showFullLoading() else hideFullLoading()
        }
    }

    companion object {
        const val TITLE_EDIT_TICKET = "Edit Weighing Ticket"
        const val SUCCESS_UPDATE_TICKET = "Ticket Updated!"
    }
}