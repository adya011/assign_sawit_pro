package com.sawitpro.weightbridge.data.model.dto

import com.squareup.moshi.Json

data class WeighingTicketDto(
    @Json(name = "date")
    val date: String? = null,

    @Json(name = "driver_name")
    val driverName: String? = null,

    @Json(name = "license_number")
    val licenseNumber: String? = null,

    @Json(name = "inbound_weight")
    val inboundWeight: Double? = null,

    @Json(name = "outbound_weight")
    val outboundWeight: Double? = null
)