package com.sawitpro.weightbridge.data.model.entity

data class RequestCreateEditWeighingTicketEntity(
    val driverName: String,
    val licenseNumber: String,
    val date: String,
    val inboundWeight: Int,
    val outboundWeight: Int,
    val netWeight: Int
)