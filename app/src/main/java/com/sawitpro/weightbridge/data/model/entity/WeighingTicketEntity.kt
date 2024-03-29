package com.sawitpro.weightbridge.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weighing_ticket")
data class WeighingTicketEntity(
    @PrimaryKey
    val uId: String,
    val driverName: String,
    val licenseNumber: String,
    val date: String,
    val inboundWeight: Double,
    val outboundWeight: Double,
    val netWeight: Double
)