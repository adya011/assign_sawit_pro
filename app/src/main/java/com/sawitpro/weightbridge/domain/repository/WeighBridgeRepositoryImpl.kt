package com.sawitpro.weightbridge.domain.repository

import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.local.dao.WeighingTicketDao
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.UpdateWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.data.remote.api.WeighingApi
import com.sawitpro.weightbridge.domain.mapper.SetWeighingDetailMapper
import com.sawitpro.weightbridge.domain.mapper.UpdateWeighingDetailMapper
import com.sawitpro.weightbridge.domain.mapper.WeighingListMapper
import com.sawitpro.weightbridge.domain.repository.core.AppDispatchers
import com.sawitpro.weightbridge.domain.repository.core.dataSourceHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class WeighBridgeRepositoryImpl(
    private val api: WeighingApi,
    private val localData: WeighingTicketDao,
    private val appDispatchers: AppDispatchers
) : WeighBridgetRepository {

    override suspend fun getWeighingDetail(uId: String): WeighingTicketEntity? =
        localData.getWeighingTicketDetail(uId)

    override suspend fun getWeighingList(isRefresh: Boolean): Flow<DataResult<List<WeighingTicketEntity>>> =
        dataSourceHandling(
            networkCall = { api.getWeighingList() },
            mapper = WeighingListMapper(),
            getFromDb = { localData.getWeighingTicketList() },
            saveToDb = { localData.insertWeighingTicket(it) },
            isGetFromApi = isRefresh
        ).flowOn(appDispatchers.io)


    override suspend fun setWeighingDetail(request: RequestCreateEditWeighingTicketEntity): Flow<DataResult<SetWeighingTicketEntity>> =
        dataSourceHandling(
            networkCall = { api.setWeighingList(request) },
            mapper = SetWeighingDetailMapper()
        ).flowOn(appDispatchers.io)

    override suspend fun updateWeighingDetail(
        uId: String,
        request: RequestCreateEditWeighingTicketEntity
    ): Flow<DataResult<UpdateWeighingTicketEntity>> =
        dataSourceHandling(
            networkCall = { api.updateWeighingList(uId, request) },
            mapper = UpdateWeighingDetailMapper()
        ).flowOn(appDispatchers.io)
}