package com.sawitpro.weightbridge.ui.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketResponseEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import com.sawitpro.weightbridge.util.Constant
import kotlinx.coroutines.launch

class WeighingCreateEditViewModel(
    private val repository: WeighBridgetRepository
) : ViewModel() {

    private val _setWeighingDetailLiveData: MutableLiveData<SetWeighingTicketResponseEntity> = MutableLiveData()
    val setWeighingDetailLiveData: LiveData<SetWeighingTicketResponseEntity> get() = _setWeighingDetailLiveData

    private val _updateWeighingDetailLiveData: MutableLiveData<WeighingTicketEntity?> = MutableLiveData()
    val updateWeighingDetailLiveData: LiveData<WeighingTicketEntity?> get() = _updateWeighingDetailLiveData

    private val _displayStateLiveData: MutableLiveData<Int> = MutableLiveData()
    val displayStateLiveData: LiveData<Int> get() = _displayStateLiveData

    private val _errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val errorMessageLiveData: LiveData<String> get() = _errorMessageLiveData

    fun setWeighingDetail(request: WeighingTicketEntity) {
        viewModelScope.launch {
            repository.setWeighingDetail(request).collect { result ->
                when (result) {
                    is DataResult.Loading -> {
                        _displayStateLiveData.value = Constant.CHILD_INDEX_LOADING
                    }

                    is DataResult.Success -> {
                        _displayStateLiveData.value = Constant.CHILD_INDEX_SUCCESS
                        _setWeighingDetailLiveData.postValue(result.body)
                    }

                    is DataResult.Error -> {
                        _displayStateLiveData.value = Constant.CHILD_INDEX_ERROR
                        _errorMessageLiveData.value = result.errorMessage
                    }
                }
            }
        }
    }

    fun updateWeighingDetail(request: WeighingTicketEntity) {
        viewModelScope.launch {
            repository.updateWeighingDetail(request).collect { result ->
                when (result) {
                    is DataResult.Loading -> {
                        _displayStateLiveData.value = Constant.CHILD_INDEX_LOADING
                    }

                    is DataResult.Success -> {
                        _displayStateLiveData.value = Constant.CHILD_INDEX_SUCCESS
                        _updateWeighingDetailLiveData.postValue(result.body)
                    }

                    is DataResult.Error -> {
                        _displayStateLiveData.value = Constant.CHILD_INDEX_ERROR
                        _errorMessageLiveData.value = result.errorMessage
                    }
                }
            }
        }
    }
}