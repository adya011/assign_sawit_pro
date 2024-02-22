package com.sawitpro.weightbridge.ui

import androidx.lifecycle.Observer
import com.sawitpro.weightbridge.base.BaseTest
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.ui.feature.detail.WeighingDetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class WeighingDetailViewModelTest : BaseTest() {

    private lateinit var viewModel: WeighingDetailViewModel

    override fun setup() {
        super.setup()
        viewModel = WeighingDetailViewModel(weighingRepo)
    }

    @Test
    fun `get weighing detail - data retrieved`() {
        /** Mock Data */
        val mockUid = "-NqxNttkoh63e0IITemA"
        val mockTicketDetailResponse = WeighingTicketEntity(
            uId = mockUid,
            date = "2024-02-22",
            driverName = "Asep Shakti Munandar",
            licenseNumber = "141231AB",
            inboundWeight = 100.0,
            outboundWeight = 98.0,
            netWeight = 2.0
        )

        val getTicketDetailObserver = mockk<Observer<WeighingTicketEntity?>>(relaxed = true)
        viewModel.ticketDetailLiveData.observeForever(getTicketDetailObserver)

        coEvery {
            weighingRepo.getWeighingDetail(mockUid)
        } returns mockTicketDetailResponse

        /** Test start */
        viewModel.getDetail(mockUid)

        /** Results */
        verify {
            getTicketDetailObserver.onChanged(viewModel.ticketDetailLiveData.value)
        }

        Assert.assertEquals(
            mockTicketDetailResponse,
            viewModel.ticketDetailLiveData.value
        )
    }

    @Test
    fun `get weighing detail - data null`() {
        /** Mock Data */
        val mockUid = "-NqxNttkoh63e0IITemA"
        val mockTicketDetailResponse: WeighingTicketEntity? = null

        val errorMessageObserver = mockk<Observer<String?>>(relaxed = true)
        viewModel.errorMessageLiveData.observeForever(errorMessageObserver)

        coEvery {
            weighingRepo.getWeighingDetail(mockUid)
        } returns mockTicketDetailResponse

        /** Test start */
        viewModel.getDetail(mockUid)

        /** Results */
        verify {
            errorMessageObserver.onChanged(viewModel.errorMessageLiveData.value)
        }

        Assert.assertEquals(
            mockTicketDetailResponse,
            viewModel.ticketDetailLiveData.value
        )
    }
}