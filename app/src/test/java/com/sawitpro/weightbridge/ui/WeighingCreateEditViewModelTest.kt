package com.sawitpro.weightbridge.ui

import androidx.lifecycle.Observer
import com.sawitpro.weightbridge.base.BaseTest
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketResponseEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.ui.feature.detail.WeighingCreateEditViewModel
import com.sawitpro.weightbridge.util.Constant
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class WeighingCreateEditViewModelTest: BaseTest() {

    private lateinit var viewModel: WeighingCreateEditViewModel

    override fun setup() {
        super.setup()
        viewModel = WeighingCreateEditViewModel(weighingRepo)
    }

    @Test
    fun `set weighing detail - success data requested to API`() {
        /** Mock Data */
        val mockSetWeighingResponse = SetWeighingTicketResponseEntity(
            name = "-Nr3o-MR0h_QQssGWA4X"
        )
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )

        val setWeighingObserver = mockk<Observer<SetWeighingTicketResponseEntity?>>(relaxed = true)
        viewModel.setWeighingDetailLiveData.observeForever(setWeighingObserver)

        val flowResult = flow { emit(DataResult.Success(mockSetWeighingResponse)) }
        coEvery { weighingRepo.setWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.setWeighingDetail(mockRequestWeighingTicket)

        /** Results */
        verify {
            setWeighingObserver.onChanged(viewModel.setWeighingDetailLiveData.value)
        }

        Assert.assertEquals(mockSetWeighingResponse, viewModel.setWeighingDetailLiveData.value)
    }

    @Test
    fun `set weighing detail - error from API`() {
        /** Mock Data */
        val mockErrorMessage = "something wrong"
        val mockErrorCode = 500
        val mockDisplayState = Constant.CHILD_INDEX_ERROR
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )

        val errorObserver = mockk<Observer<String?>>(relaxed = true)
        viewModel.errorMessageLiveData.observeForever(errorObserver)

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Error<SetWeighingTicketResponseEntity>(mockErrorMessage, mockErrorCode)) }
        coEvery { weighingRepo.setWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.setWeighingDetail(mockRequestWeighingTicket)

        /** Results */
        verify {
            displayStateObserver.onChanged(viewModel.displayStateLiveData.value)
            errorObserver.onChanged(viewModel.errorMessageLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, Constant.CHILD_INDEX_ERROR)
        Assert.assertEquals(mockErrorMessage, "something wrong")
    }

    @Test
    fun `set weighing list - loading`() {
        /** Mock Data */
        val mockDisplayState = Constant.CHILD_INDEX_LOADING
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Loading<SetWeighingTicketResponseEntity>()) }
        coEvery { weighingRepo.setWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.setWeighingDetail(mockRequestWeighingTicket)

        /** Results */
        verify {
            displayStateObserver.onChanged(viewModel.displayStateLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, Constant.CHILD_INDEX_LOADING)
    }

    @Test
    fun `update weighing detail - success data requested to API`() {
        /** Mock Data */
        val mockUpdateWeighingResponse = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )

        val updateWeighingObserver = mockk<Observer<WeighingTicketEntity?>>(relaxed = true)
        viewModel.updateWeighingDetailLiveData.observeForever(updateWeighingObserver)

        val flowResult = flow { emit(DataResult.Success(mockUpdateWeighingResponse)) }
        coEvery { weighingRepo.updateWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.updateWeighingDetail(mockRequestWeighingTicket)

        /** Results */
        verify {
            updateWeighingObserver.onChanged(viewModel.updateWeighingDetailLiveData.value)
        }

        Assert.assertEquals(mockUpdateWeighingResponse, viewModel.updateWeighingDetailLiveData.value)
    }

    @Test
    fun `update weighing detail - error from API`() {
        /** Mock Data */
        val mockErrorMessage = "something wrong"
        val mockErrorCode = 500
        val mockDisplayState = Constant.CHILD_INDEX_ERROR
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )

        val errorObserver = mockk<Observer<String?>>(relaxed = true)
        viewModel.errorMessageLiveData.observeForever(errorObserver)

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Error<WeighingTicketEntity>(mockErrorMessage, mockErrorCode)) }
        coEvery { weighingRepo.updateWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.updateWeighingDetail(mockRequestWeighingTicket)

        /** Results */
        verify {
            displayStateObserver.onChanged(viewModel.displayStateLiveData.value)
            errorObserver.onChanged(viewModel.errorMessageLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, Constant.CHILD_INDEX_ERROR)
        Assert.assertEquals(mockErrorMessage, "something wrong")
    }

    @Test
    fun `update weighing list - loading`() {
        /** Mock Data */
        val mockDisplayState = Constant.CHILD_INDEX_LOADING
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Loading<WeighingTicketEntity>()) }
        coEvery { weighingRepo.updateWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.updateWeighingDetail(mockRequestWeighingTicket)

        /** Results */
        verify {
            displayStateObserver.onChanged(viewModel.displayStateLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, Constant.CHILD_INDEX_LOADING)
    }
}