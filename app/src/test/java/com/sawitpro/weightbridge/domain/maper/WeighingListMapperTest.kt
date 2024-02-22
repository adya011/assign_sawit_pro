package com.sawitpro.weightbridge.domain.maper

import com.sawitpro.weightbridge.data.model.dto.WeighingTicketDto
import com.sawitpro.weightbridge.domain.mapper.WeighingListMapper
import org.junit.Assert
import org.junit.Test

class WeighingListMapperTest {
    private val mapper = WeighingListMapper()

    @Test
    fun `map weighing list from dto to entity`() {
        val dtoData = mapOf(
            "-NqxNttkoh63e0IITemA" to WeighingTicketDto(
                driverName = "driver1",
                licenseNumber = "AAA",
                date = "13 Sep 23",
                inboundWeight = 13.0,
                outboundWeight = 12.0
            ),
            "-Nr1DQZKrjc-gfcJbQCE" to WeighingTicketDto(
                driverName = "Yayan",
                licenseNumber = "141231AC",
                date = "2024-02-09",
                inboundWeight = 15.0,
                outboundWeight = 14.0
            )
        )
        val result = mapper.mapDataModel(dtoData)

        Assert.assertEquals(dtoData.keys.first(), result.first().uId)
        Assert.assertEquals(dtoData["-NqxNttkoh63e0IITemA"]?.date, result.first().date)
        Assert.assertEquals(dtoData["-NqxNttkoh63e0IITemA"]?.driverName, result.first().driverName)
        Assert.assertEquals(dtoData["-NqxNttkoh63e0IITemA"]?.licenseNumber, result.first().licenseNumber)
        Assert.assertEquals(dtoData["-NqxNttkoh63e0IITemA"]?.inboundWeight, result.first().inboundWeight)
        Assert.assertEquals(dtoData["-NqxNttkoh63e0IITemA"]?.outboundWeight, result.first().outboundWeight)
    }
}