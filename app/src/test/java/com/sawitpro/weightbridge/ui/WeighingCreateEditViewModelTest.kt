package com.sawitpro.weightbridge.ui

import androidx.lifecycle.Observer
import com.sawitpro.weightbridge.base.BaseTest
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.UpdateWeighingTicketEntity
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
        val mockSetWeighingResponse = SetWeighingTicketEntity(
            name = "-Nr3o-MR0h_QQssGWA4X"
        )
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12,
            netWeight = 1
        )

        val setWeighingObserver = mockk<Observer<SetWeighingTicketEntity?>>(relaxed = true)
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
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12,
            netWeight = 1
        )

        val errorObserver = mockk<Observer<String?>>(relaxed = true)
        viewModel.errorMessageLiveData.observeForever(errorObserver)

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Error<SetWeighingTicketEntity>(mockErrorMessage, mockErrorCode)) }
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
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12,
            netWeight = 1
        )

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Loading<SetWeighingTicketEntity>()) }
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
        val mockUpdateWeighingResponse = UpdateWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12,
            netWeight = 1
        )
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12,
            netWeight = 1
        )

        val updateWeighingObserver = mockk<Observer<UpdateWeighingTicketEntity?>>(relaxed = true)
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
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12,
            netWeight = 1
        )

        val errorObserver = mockk<Observer<String?>>(relaxed = true)
        viewModel.errorMessageLiveData.observeForever(errorObserver)

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Error<UpdateWeighingTicketEntity>(mockErrorMessage, mockErrorCode)) }
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
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12,
            netWeight = 1
        )

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Loading<UpdateWeighingTicketEntity>()) }
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