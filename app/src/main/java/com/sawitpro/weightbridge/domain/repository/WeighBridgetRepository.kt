package com.sawitpro.weightbridge.domain.repository

import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.TruckDataEntity
import kotlinx.coroutines.flow.Flow

interface WeighBridgetRepository {
    suspend fun getWeighingList(): Flow<DataResult<List<TruckDataEntity>>>
}