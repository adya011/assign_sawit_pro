package com.sawitpro.weightbridge.ui.feature.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.TruckDataEntity
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import com.sawitpro.weightbridge.util.Constant.CHILD_INDEX_ERROR
import com.sawitpro.weightbridge.util.Constant.CHILD_INDEX_LOADING
import com.sawitpro.weightbridge.util.Constant.CHILD_INDEX_SUCCESS
import kotlinx.coroutines.launch

class WeighingListViewModel(
    private val repository: WeighBridgetRepository
) : ViewModel() {

    private val _weighingListLiveData: MutableLiveData<List<WeighingTicketEntity>> = MutableLiveData()
    val weighingListLiveData: LiveData<List<WeighingTicketEntity>> get() = _weighingListLiveData

    private val _displayStateLiveData: MutableLiveData<Int> = MutableLiveData()
    val displayStateLiveData: LiveData<Int> get() = _displayStateLiveData

    private val _errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val errorMessageLiveData: LiveData<String> get() = _errorMessageLiveData

    fun fetchWeighingList() {
        viewModelScope.launch {
            repository.getWeighingList().collect { result ->
                when (result) {
                    is DataResult.Loading -> {
                        _displayStateLiveData.value = CHILD_INDEX_LOADING
                    }

                    is DataResult.Success -> {
                        _displayStateLiveData.value = CHILD_INDEX_SUCCESS
                        _weighingListLiveData.postValue(result.body)
                    }

                    is DataResult.Error -> {
                        _displayStateLiveData.value = CHILD_INDEX_ERROR
                        _errorMessageLiveData.value = result.errorMessage
                    }
                }
            }
        }
    }
}