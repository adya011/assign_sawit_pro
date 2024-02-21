package com.sawitpro.weightbridge.domain.repository

import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.local.dao.WeighingTicketDao
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketResponseEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.data.remote.api.WeighingApi
import com.sawitpro.weightbridge.domain.mapper.SetWeighingDetailMapper
import com.sawitpro.weightbridge.domain.mapper.UpdateWeighingDetailMapper
import com.sawitpro.weightbridge.domain.mapper.WeighingListMapper
import com.sawitpro.weightbridge.domain.repository.core.dataSourceHandling
import kotlinx.coroutines.flow.Flow

class WeighBridgeRepositoryImpl(
    private val api: WeighingApi,
    private val localData: WeighingTicketDao
) : WeighBridgetRepository {

    override suspend fun getWeighingList(): Flow<DataResult<List<WeighingTicketEntity>>> =
        dataSourceHandling(
            networkCall = { api.getWeighingList() },
            mapper = WeighingListMapper()
        )

    override suspend fun setWeighingDetail(request: WeighingTicketEntity): Flow<DataResult<SetWeighingTicketResponseEntity>> =
        dataSourceHandling(
            networkCall = { api.setWeighingList(request) },
            mapper = SetWeighingDetailMapper()
        )

    override suspend fun updateWeighingDetail(request: WeighingTicketEntity): Flow<DataResult<WeighingTicketEntity>> =
        dataSourceHandling(
            networkCall = { api.updateWeighingList(request) },
            mapper = UpdateWeighingDetailMapper()
        )
}