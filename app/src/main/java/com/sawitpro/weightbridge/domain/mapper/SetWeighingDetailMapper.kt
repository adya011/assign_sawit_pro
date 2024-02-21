package com.sawitpro.weightbridge.domain.mapper

import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketResponseEntity
import com.sawitpro.weightbridge.domain.repository.core.DataMapper

class SetWeighingDetailMapper : DataMapper<SetWeighingResponseDto, SetWeighingTicketResponseEntity> {
    override fun mapDataModel(dataModel: SetWeighingResponseDto?): SetWeighingTicketResponseEntity =
        SetWeighingTicketResponseEntity(
            name = dataModel?.name.orEmpty()
        )
}