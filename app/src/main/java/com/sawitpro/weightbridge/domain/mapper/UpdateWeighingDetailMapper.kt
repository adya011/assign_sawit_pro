package com.sawitpro.weightbridge.domain.mapper

import com.sawitpro.weightbridge.data.model.dto.WeighingTicketDto
import com.sawitpro.weightbridge.data.model.entity.UpdateWeighingTicketEntity
import com.sawitpro.weightbridge.domain.repository.core.DataMapper
import com.sawitpro.weightbridge.util.orZero

class UpdateWeighingDetailMapper : DataMapper<WeighingTicketDto, UpdateWeighingTicketEntity> {
    override fun mapDataModel(dataModel: WeighingTicketDto?): UpdateWeighingTicketEntity =
        UpdateWeighingTicketEntity(
            date = dataModel?.date.orEmpty(),
            driverName = dataModel?.driverName.orEmpty(),
            licenseNumber = dataModel?.licenseNumber.orEmpty(),
            inboundWeight = dataModel?.inboundWeight.orZero(),
            outboundWeight = dataModel?.outboundWeight.orZero(),
            netWeight = dataModel?.inboundWeight.orZero() - dataModel?.outboundWeight.orZero()
        )
}