package com.sawitpro.weightbridge.di

import com.sawitpro.weightbridge.data.local.core.AppDatabase
import com.sawitpro.weightbridge.data.local.dao.WeighingTicketDao

class RoomModule(private val appDatabase: AppDatabase) {
    fun weighingTicketDao(): WeighingTicketDao = appDatabase.weighingTicketDao
}