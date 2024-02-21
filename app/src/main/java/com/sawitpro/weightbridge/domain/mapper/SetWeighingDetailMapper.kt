package com.sawitpro.weightbridge.domain.mapper

import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketEntity
import com.sawitpro.weightbridge.domain.repository.core.DataMapper

class SetWeighingDetailMapper : DataMapper<SetWeighingResponseDto, SetWeighingTicketEntity> {
    override fun mapDataModel(dataModel: SetWeighingResponseDto?): SetWeighingTicketEntity =
        SetWeighingTicketEntity(
            name = dataModel?.name.orEmpty()
        )
}