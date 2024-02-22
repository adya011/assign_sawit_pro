package com.sawitpro.weightbridge.data.model.entity

data class UpdateWeighingTicketEntity(
    val driverName: String,
    val licenseNumber: String,
    val date: String,
    val inboundWeight: Double,
    val outboundWeight: Double,
    val netWeight: Double
)