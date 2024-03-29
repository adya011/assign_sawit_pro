package com.sawitpro.weightbridge.ui

import androidx.lifecycle.Observer
import com.sawitpro.weightbridge.base.BaseTest
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.ui.feature.list.WeighingListViewModel
import com.sawitpro.weightbridge.util.Constant.CHILD_INDEX_WARNING
import com.sawitpro.weightbridge.util.Constant.CHILD_INDEX_LOADING
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class WeighingListViewModelTest : BaseTest() {

    private lateinit var viewModel: WeighingListViewModel

    override fun setup() {
        super.setup()
        viewModel = WeighingListViewModel(weighingRepo)
    }

    @Test
    fun `fetch weighing list - success data retrieved`() {
        /** Mock Data */
        val mockWeighingResultData = listOf(
            WeighingTicketEntity(
                uId = "-NqxNttkoh63e0IITemA",
                driverName = "driver1",
                licenseNumber = "AAA",
                date = "13 Sep 23",
                inboundWeight = 13.0,
                outboundWeight = 12.0,
                netWeight = 1.0
            ),
            WeighingTicketEntity(
                uId = "-NqxNttkoh63e0IITemB",
                driverName = "driver2",
                licenseNumber = "BBB",
                date = "20 Jan 23",
                inboundWeight = 23.0,
                outboundWeight = 21.0,
                netWeight = 1.0
            )
        )

        val weighingListObserver = mockk<Observer<List<WeighingTicketEntity>?>>(relaxed = true)
        viewModel.weighingListLiveData.observeForever(weighingListObserver)

        val flowResult = flow { emit(DataResult.Success(mockWeighingResultData)) }
        coEvery { weighingRepo.getWeighingList(false) } returns flowResult

        /** Test start */
        viewModel.fetchWeighingList()

        /** Results */
        verify {
            weighingListObserver.onChanged(viewModel.weighingListLiveData.value)
        }

        Assert.assertEquals(mockWeighingResultData, viewModel.weighingListLiveData.value)
        Assert.assertEquals(2, viewModel.weighingListLiveData.value?.count())
    }

    @Test
    fun `fetch weighing list - success empty data retrieved`() {
        /** Mock Data */
        val mockWeighingResultData = emptyList<WeighingTicketEntity>()

        val weighingListObserver = mockk<Observer<List<WeighingTicketEntity>?>>(relaxed = true)
        viewModel.weighingListLiveData.observeForever(weighingListObserver)

        val flowResult = flow { emit(DataResult.Success(mockWeighingResultData)) }
        coEvery { weighingRepo.getWeighingList(false) } returns flowResult

        /** Test start */
        viewModel.fetchWeighingList()

        /** Results */
        verify {
            weighingListObserver.onChanged(viewModel.weighingListLiveData.value)
        }

        Assert.assertEquals(mockWeighingResultData, viewModel.weighingListLiveData.value)
        Assert.assertEquals(0, viewModel.weighingListLiveData.value?.count())
    }

    @Test
    fun `fetch weighing list - error`() {
        /** Mock Data */
        val mockErrorMessage = "something wrong"
        val mockErrorCode = 500
        val mockDisplayState = CHILD_INDEX_WARNING

        val errorObserver = mockk<Observer<String?>>(relaxed = true)
        viewModel.warningMessageLiveData.observeForever(errorObserver)

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Error<List<WeighingTicketEntity>>(mockErrorMessage, mockErrorCode)) }
        coEvery { weighingRepo.getWeighingList(false) } returns flowResult

        /** Test start */
        viewModel.fetchWeighingList()

        /** Results */
        verify {
            displayStateObserver.onChanged(viewModel.displayStateLiveData.value)
            errorObserver.onChanged(viewModel.warningMessageLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, CHILD_INDEX_WARNING)
        Assert.assertEquals(mockErrorMessage, "something wrong")
    }

    @Test
    fun `fetch weighing list - loading`() {
        /** Mock Data */
        val mockDisplayState = CHILD_INDEX_LOADING

        val displayStateObserver = mockk<Observer<Int?>>(relaxed = true)
        viewModel.displayStateLiveData.observeForever(displayStateObserver)

        val flowResult = flow { emit(DataResult.Loading<List<WeighingTicketEntity>>()) }
        coEvery { weighingRepo.getWeighingList(false) } returns flowResult

        /** Test start */
        viewModel.fetchWeighingList()

        /** Results */
        verify {
            displayStateObserver.onChanged(viewModel.displayStateLiveData.value)
        }

        Assert.assertEquals(mockDisplayState, CHILD_INDEX_LOADING)
    }
}