package com.sawitpro.weightbridge.ui

import androidx.lifecycle.Observer
import com.sawitpro.weightbridge.base.BaseTest
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketEntity
import com.sawitpro.weightbridge.ui.feature.detail.WeighingCreateViewModel
import com.sawitpro.weightbridge.util.Constant
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class WeighingCreateViewModelTest : BaseTest() {

    private lateinit var viewModel: WeighingCreateViewModel

    override fun setup() {
        super.setup()
        viewModel = WeighingCreateViewModel(weighingRepo)
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
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        val setWeighingObserver = mockk<Observer<SetWeighingTicketEntity?>>(relaxed = true)
        viewModel.setWeighingDetailLiveData.observeForever(setWeighingObserver)

        val flowResult = flow { emit(DataResult.Success(mockSetWeighingResponse)) }
        coEvery { weighingRepo.setWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.createWeighingDetail(mockRequestWeighingTicket)

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

        val flowResult = flow { emit(DataResult.Error<SetWeighingTicketEntity>(mockErrorMessage, mockErrorCode)) }
        coEvery { weighingRepo.setWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.createWeighingDetail(mockRequestWeighingTicket)

        /** Results */
        verify {
            loadingObserver.onChanged(viewModel.loadingLiveData.value)
            errorObserver.onChanged(viewModel.errorMessageLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, Constant.CHILD_INDEX_WARNING)
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
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        val loadingObserver = mockk<Observer<Boolean?>>(relaxed = true)
        viewModel.loadingLiveData.observeForever(loadingObserver)

        val flowResult = flow { emit(DataResult.Loading<SetWeighingTicketEntity>()) }
        coEvery { weighingRepo.setWeighingDetail(mockRequestWeighingTicket) } returns flowResult

        /** Test start */
        viewModel.createWeighingDetail(mockRequestWeighingTicket)

        /** Results */
        verify {
            loadingObserver.onChanged(viewModel.loadingLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, Constant.CHILD_INDEX_LOADING)
    }
}