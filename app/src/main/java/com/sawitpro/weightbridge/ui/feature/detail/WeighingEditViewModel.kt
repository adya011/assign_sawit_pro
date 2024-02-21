package com.sawitpro.weightbridge.ui.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.UpdateWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import com.sawitpro.weightbridge.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeighingEditViewModel(
    private val repository: WeighBridgetRepository
) : ViewModel() {

    private val _updateWeighingDetailLiveData: MutableLiveData<UpdateWeighingTicketEntity?> =
        MutableLiveData()
    val updateWeighingDetailLiveData: LiveData<UpdateWeighingTicketEntity?> get() = _updateWeighingDetailLiveData

    private val _ticketDetailLiveData: MutableLiveData<WeighingTicketEntity> = MutableLiveData()
    val ticketDetailLiveData: LiveData<WeighingTicketEntity> get() = _ticketDetailLiveData

    private val _displayStateLiveData: MutableLiveData<Int> = MutableLiveData()
    val displayStateLiveData: LiveData<Int> get() = _displayStateLiveData

    private val _errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val errorMessageLiveData: LiveData<String> get() = _errorMessageLiveData

    fun getDetail(uId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _ticketDetailLiveData.postValue(repository.getWeighingDetail(uId))
        }
    }

    fun updateWeighingDetail(uId: String, request: RequestCreateEditWeighingTicketEntity) {
        viewModelScope.launch {
            repository.updateWeighingDetail(uId, request).collect { result ->
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