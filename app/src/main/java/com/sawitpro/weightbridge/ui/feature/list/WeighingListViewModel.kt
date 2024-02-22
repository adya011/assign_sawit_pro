package com.sawitpro.weightbridge.ui.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import com.sawitpro.weightbridge.util.Constant.CHILD_INDEX_WARNING
import com.sawitpro.weightbridge.util.Constant.CHILD_INDEX_LOADING
import com.sawitpro.weightbridge.util.Constant.CHILD_INDEX_SUCCESS
import com.sawitpro.weightbridge.util.Constant.DATA_IS_EMPTY
import kotlinx.coroutines.launch

class WeighingListViewModel(
    private val repository: WeighBridgetRepository
) : ViewModel() {

    private val _weighingListLiveData: MutableLiveData<List<WeighingTicketEntity>> = MutableLiveData()
    val weighingListLiveData: LiveData<List<WeighingTicketEntity>> get() = _weighingListLiveData

    private val _displayStateLiveData: MutableLiveData<Int> = MutableLiveData()
    val displayStateLiveData: LiveData<Int> get() = _displayStateLiveData

    private val _warningMessageLiveData: MutableLiveData<String?> = MutableLiveData()
    val warningMessageLiveData: LiveData<String?> get() = _warningMessageLiveData

    init {
        fetchWeighingList()
    }

    fun fetchWeighingList(isRefresh: Boolean = false) {
        viewModelScope.launch {
            repository.getWeighingList(isRefresh).collect { result ->
                when (result) {
                    is DataResult.Loading -> {
                        _displayStateLiveData.value = CHILD_INDEX_LOADING
                    }

                    is DataResult.Success -> {
                        val data = result.body

                        if (data.isEmpty()) {
                            _displayStateLiveData.value = CHILD_INDEX_WARNING
                            _warningMessageLiveData.value = DATA_IS_EMPTY
                        } else {
                            _displayStateLiveData.value = CHILD_INDEX_SUCCESS
                            _weighingListLiveData.value = data
                        }
                    }

                    is DataResult.Error -> {
                        _displayStateLiveData.value = CHILD_INDEX_WARNING
                        _warningMessageLiveData.value = result.errorMessage
                    }
                }
            }
        }
    }
}