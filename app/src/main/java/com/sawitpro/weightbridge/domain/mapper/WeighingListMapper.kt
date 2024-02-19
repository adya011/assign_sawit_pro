package com.sawitpro.weightbridge.domain.mapper

import com.sawitpro.weightbridge.data.model.dto.TruckDataDto
import com.sawitpro.weightbridge.data.model.entity.TruckDataEntity
import com.sawitpro.weightbridge.domain.repository.core.DataMapper

class WeighingListMapper : DataMapper<Map<String, TruckDataDto>, List<TruckDataEntity>> {
    override fun mapDataModel(dataModel: Map<String, TruckDataDto>?): List<TruckDataEntity> =
        dataModel?.values?.map {
            TruckDataEntity(
                id = it.id,
                date = it.date,
                driverName = it.driverName,
                licenseNumber = it.licenseNumber
            )
        } ?: emptyList()
}