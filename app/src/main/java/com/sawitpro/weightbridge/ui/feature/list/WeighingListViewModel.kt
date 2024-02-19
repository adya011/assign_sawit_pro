package com.sawitpro.weightbridge.ui.feature.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.TruckDataEntity
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import kotlinx.coroutines.launch

class WeighingListViewModel(
    private val repository: WeighBridgetRepository
) : ViewModel() {

    private val _weighingListLiveData: MutableLiveData<List<TruckDataEntity>> = MutableLiveData()
    val weighingListLiveData: LiveData<List<TruckDataEntity>> get() = _weighingListLiveData

    fun fetchWeighingList() {
        viewModelScope.launch {
            repository.getWeighingList().collect { result ->
                when (result) {
                    is DataResult.Loading -> {
                        Log.d("nandaDebug", "loading...")
                    }

                    is DataResult.Success -> {
                        Log.d("nandaDebug", "success, ${result.body}")
                        _weighingListLiveData.postValue(result.body)
                    }

                    is DataResult.Error -> {
                        Log.d("nandaDebug", "error, message: ${result.errorMessage}")
                    }
                }
            }
        }
    }
}