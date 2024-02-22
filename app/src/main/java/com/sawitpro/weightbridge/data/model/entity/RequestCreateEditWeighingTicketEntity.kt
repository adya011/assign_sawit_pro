package com.sawitpro.weightbridge.data.model.entity

import com.squareup.moshi.Json

data class RequestCreateEditWeighingTicketEntity(
    @Json(name = "driver_name")
    val driverName: String,

    @Json(name = "license_number")
    val licenseNumber: String,

    @Json(name = "date")
    val date: String,

    @Json(name = "inbound_weight")
    val inboundWeight: Double,

    @Json(name = "outbound_weight")
    val outboundWeight: Double,

    @Json(name = "net_weight")
    val netWeight: Double
)