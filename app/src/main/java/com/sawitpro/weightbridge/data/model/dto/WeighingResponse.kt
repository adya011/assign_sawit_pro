package com.sawitpro.weightbridge.data.model.dto

import com.squareup.moshi.Json

data class WeighingResponse(
    @Json(name = "date")
    val date: String,
    @Json(name = "driver_name")
    val driverName: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "license_number")
    val licenseNumber: String,
    @Json(name = "truck_type")
    val truckType: String
)