package com.sawitpro.weightbridge.domain.repository

import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.TruckDataEntity
import com.sawitpro.weightbridge.data.remote.api.WeighingApi
import com.sawitpro.weightbridge.domain.mapper.WeighingListMapper
import com.sawitpro.weightbridge.domain.repository.core.dataSourceHandling
import kotlinx.coroutines.flow.Flow

class WeighBridgeRepositoryImpl(private val api: WeighingApi) : WeighBridgetRepository {
    override suspend fun getWeighingList(): Flow<DataResult<List<TruckDataEntity>>> =
        dataSourceHandling(
            networkCall = { api.getWeighingList() },
            mapper = WeighingListMapper(),
        )
}