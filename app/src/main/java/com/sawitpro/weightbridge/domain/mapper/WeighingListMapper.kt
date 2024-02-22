package com.sawitpro.weightbridge.domain.mapper

import com.sawitpro.weightbridge.data.model.dto.WeighingTicketDto
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.domain.repository.core.DataMapper
import com.sawitpro.weightbridge.util.format
import com.sawitpro.weightbridge.util.orZero

class WeighingListMapper : DataMapper<Map<String, WeighingTicketDto>, List<WeighingTicketEntity>> {
    override fun mapDataModel(dataModel: Map<String, WeighingTicketDto>?): List<WeighingTicketEntity> =
        dataModel?.map {
            WeighingTicketEntity(
                uId = it.key,
                date = it.value.date.orEmpty(),
                driverName = it.value.driverName.orEmpty(),
                licenseNumber = it.value.licenseNumber.orEmpty(),
                inboundWeight = it.value.inboundWeight.orZero(),
                outboundWeight = it.value.outboundWeight.orZero(),
                netWeight = (it.value.inboundWeight.orZero() - it.value.outboundWeight.orZero()).format()
            )
        } ?: emptyList()
}