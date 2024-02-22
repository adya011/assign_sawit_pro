package com.sawitpro.weightbridge.domain.maper

import com.sawitpro.weightbridge.data.model.dto.WeighingTicketDto
import com.sawitpro.weightbridge.domain.mapper.UpdateWeighingDetailMapper
import org.junit.Assert
import org.junit.Test

class UpdateWEighingDetailMapper {
    private val mapper = UpdateWeighingDetailMapper()

    @Test
    fun `map update weighing from dto to entity`() {
        val dtoData = WeighingTicketDto(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )
        val result = mapper.mapDataModel(dtoData)

        Assert.assertEquals(dtoData.date, result.date)
        Assert.assertEquals(dtoData.driverName, result.driverName)
        Assert.assertEquals(dtoData.licenseNumber, result.licenseNumber)
        Assert.assertEquals(dtoData.inboundWeight, result.inboundWeight)
        Assert.assertEquals(dtoData.outboundWeight, result.outboundWeight)
    }
}