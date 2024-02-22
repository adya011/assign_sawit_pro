package com.sawitpro.weightbridge.ui.feature.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.util.getCurrentDate
import com.sawitpro.weightbridge.util.setGone
import com.sawitpro.weightbridge.util.toDouble
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeighingCreateFragment : AbstractWeighingCreateEditFragment() {

    private val viewModel by viewModel<WeighingCreateViewModel>()

    override fun getToolbarTitle(): String = TITLE_CREATE_TICKET

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupView()
    }

    override fun submitData() = with(binding) {
        viewModel.createWeighingDetail(
            RequestCreateEditWeighingTicketEntity(
                driverName = etDriverName.text.toString(),
                licenseNumber = etLicenseNum.text.toString(),
                date = getCurrentDate(),
                inboundWeight = etInboundWeight.text.toDouble(),
                outboundWeight = etOutboundWeight.text.toDouble()
            )
        )
    }

    private fun setupView() = with(binding) {
        tvLabelId.setGone()
        tvValueId.setGone()
        btnApply.isEnabled = true
    }

    private fun setupObserver() {
        viewModel.setWeighingDetailLiveData.observe(viewLifecycleOwner) {
            showToast(SUCCESS_CREATE_TICKET)
            findNavController().previousBackStackEntry?.savedStateHandle?.set(NAV_RESULT_UPDATED, true)
            findNavController().popBackStack()
        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) showFullLoading() else hideFullLoading()
        }
    }

    companion object {
        const val TITLE_CREATE_TICKET = "Create Weighing Ticket"
        const val SUCCESS_CREATE_TICKET = "Success Create Ticket"
    }
}