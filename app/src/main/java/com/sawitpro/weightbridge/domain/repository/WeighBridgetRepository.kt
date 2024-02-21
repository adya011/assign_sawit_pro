package com.sawitpro.weightbridge.domain.repository

import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.UpdateWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import kotlinx.coroutines.flow.Flow

interface WeighBridgetRepository {

    suspend fun getWeighingDetail(uId: String): WeighingTicketEntity

    suspend fun getWeighingList(): Flow<DataResult<List<WeighingTicketEntity>>>

    suspend fun setWeighingDetail(
        request: RequestCreateEditWeighingTicketEntity
    ): Flow<DataResult<SetWeighingTicketEntity>>

    suspend fun updateWeighingDetail(
        request: RequestCreateEditWeighingTicketEntity
    ): Flow<DataResult<UpdateWeighingTicketEntity>>
}