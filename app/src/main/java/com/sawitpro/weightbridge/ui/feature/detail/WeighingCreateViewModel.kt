package com.sawitpro.weightbridge.ui.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketEntity
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import kotlinx.coroutines.launch

class WeighingCreateViewModel(
    private val repository: WeighBridgetRepository
) : ViewModel() {

    private val _setWeighingDetailLiveData: MutableLiveData<SetWeighingTicketEntity?> = MutableLiveData()
    val setWeighingDetailLiveData: LiveData<SetWeighingTicketEntity?> get() = _setWeighingDetailLiveData

    private val _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val errorMessageLiveData: LiveData<String> get() = _errorMessageLiveData

    fun createWeighingDetail(request: RequestCreateEditWeighingTicketEntity) {
        viewModelScope.launch {
            repository.setWeighingDetail(request).collect { result ->
                when (result) {
                    is DataResult.Loading -> {
                        _loadingLiveData.value = true
                    }

                    is DataResult.Success -> {
                        _loadingLiveData.value = false
                        _setWeighingDetailLiveData.postValue(result.body)
                    }

                    is DataResult.Error -> {
                        _loadingLiveData.value = false
                        _errorMessageLiveData.value = result.errorMessage
                    }
                }
            }
        }
    }
}