package com.sawitpro.weightbridge.ui.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeighingDetailViewModel(
    private val repository: WeighBridgetRepository
) : ViewModel() {

    private val _ticketDetailLiveData: MutableLiveData<WeighingTicketEntity> = MutableLiveData()
    val ticketDetailLiveData: LiveData<WeighingTicketEntity> get() = _ticketDetailLiveData

    fun getDetail(uId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _ticketDetailLiveData.postValue(repository.getWeighingDetail(uId))
        }
    }
}