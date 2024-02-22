package com.sawitpro.weightbridge.ui

import androidx.lifecycle.Observer
import com.sawitpro.weightbridge.base.BaseTest
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.UpdateWeighingTicketEntity
import com.sawitpro.weightbridge.ui.feature.detail.WeighingEditViewModel
import com.sawitpro.weightbridge.util.Constant
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class WeighingEditViewModelTest : BaseTest() {

    private lateinit var viewModel: WeighingEditViewModel

    override fun setup() {
        super.setup()
        viewModel = WeighingEditViewModel(weighingRepo)
    }

    @Test
    fun `update weighing detail - success data retrieved`() {
        /** Mock Data */
        val mockUid = "-NqxNttkoh63e0IITemA"
        val mockUpdateWeighingResponse = UpdateWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13.0,
            outboundWeight = 12.0,
            netWeight = 1.0
        )
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        val updateWeighingObserver = mockk<Observer<UpdateWeighingTicketEntity?>>(relaxed = true)
        viewModel.updateWeighingDetailLiveData.observeForever(updateWeighingObserver)

        val flowResult = flow { emit(DataResult.Success(mockUpdateWeighingResponse)) }
        coEvery {
            weighingRepo.updateWeighingDetail(
                mockUid,
                mockRequestWeighingTicket
            )
        } returns flowResult

        /** Test start */
        viewModel.updateWeighingDetail(mockUid, mockRequestWeighingTicket)

        /** Results */
        verify {
            updateWeighingObserver.onChanged(viewModel.updateWeighingDetailLiveData.value)
        }

        Assert.assertEquals(
            mockUpdateWeighingResponse,
            viewModel.updateWeighingDetailLiveData.value
        )
    }

    @Test
    fun `update weighing detail - error`() {
        /** Mock Data */
        val mockUid = "-NqxNttkoh63e0IITemA"
        val mockErrorMessage = "something wrong"
        val mockErrorCode = 500
        val mockDisplayState = Constant.CHILD_INDEX_WARNING
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        val errorObserver = mockk<Observer<String?>>(relaxed = true)
        viewModel.errorMessageLiveData.observeForever(errorObserver)

        val loadingObserver = mockk<Observer<Boolean?>>(relaxed = true)
        viewModel.loadingLiveData.observeForever(loadingObserver)

        val flowResult = flow {
            emit(
                DataResult.Error<UpdateWeighingTicketEntity>(
                    mockErrorMessage,
                    mockErrorCode
                )
            )
        }
        coEvery {
            weighingRepo.updateWeighingDetail(
                mockUid,
                mockRequestWeighingTicket
            )
        } returns flowResult

        /** Test start */
        viewModel.updateWeighingDetail(mockUid, mockRequestWeighingTicket)

        /** Results */
        verify {
            loadingObserver.onChanged(viewModel.loadingLiveData.value)
            errorObserver.onChanged(viewModel.errorMessageLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, Constant.CHILD_INDEX_WARNING)
        Assert.assertEquals(mockErrorMessage, "something wrong")
    }

    @Test
    fun `update weighing list - loading`() {
        /** Mock Data */
        val mockUid = "-NqxNttkoh63e0IITemA"
        val mockDisplayState = Constant.CHILD_INDEX_LOADING
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        val loadingObserver = mockk<Observer<Boolean?>>(relaxed = true)
        viewModel.loadingLiveData.observeForever(loadingObserver)

        val flowResult = flow { emit(DataResult.Loading<UpdateWeighingTicketEntity>()) }
        coEvery {
            weighingRepo.updateWeighingDetail(
                mockUid,
                mockRequestWeighingTicket
            )
        } returns flowResult

        /** Test start */
        viewModel.updateWeighingDetail(mockUid, mockRequestWeighingTicket)

        /** Results */
        verify {
            loadingObserver.onChanged(viewModel.loadingLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, Constant.CHILD_INDEX_LOADING)
    }
}