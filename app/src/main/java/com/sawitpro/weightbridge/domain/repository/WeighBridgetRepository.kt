package com.sawitpro.weightbridge.domain.repository

import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketResponseEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import kotlinx.coroutines.flow.Flow

interface WeighBridgetRepository {
    suspend fun getWeighingList(): Flow<DataResult<List<WeighingTicketEntity>>>

    suspend fun setWeighingDetail(
        request: WeighingTicketEntity
    ): Flow<DataResult<SetWeighingTicketResponseEntity>>

    suspend fun updateWeighingDetail(
        request: WeighingTicketEntity
    ): Flow<DataResult<WeighingTicketEntity>>
}