package com.sawitpro.weightbridge.domain.maper

import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.domain.mapper.SetWeighingDetailMapper
import org.junit.Assert
import org.junit.Test

class SetWeighingDetailMapper {
    private val mapper = SetWeighingDetailMapper()

    @Test
    fun `map set weighing detail from dto to entity`() {
        val dtoData = SetWeighingResponseDto(
            name = "-NqxNttkoh63e0IITemA"
        )
        val result = mapper.mapDataModel(dtoData)

        Assert.assertEquals(dtoData.name, result.name)
    }
}