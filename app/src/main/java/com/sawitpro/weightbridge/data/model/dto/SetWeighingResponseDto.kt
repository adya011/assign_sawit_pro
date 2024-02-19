package com.sawitpro.weightbridge.data.model.dto

import com.squareup.moshi.Json

data class SetWeighingResponseDto(
    @Json(name = "name")
    val name: String? = null
)