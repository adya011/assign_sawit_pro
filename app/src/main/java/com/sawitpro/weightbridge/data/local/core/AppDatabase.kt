package com.sawitpro.weightbridge.data.local.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sawitpro.weightbridge.data.local.dao.WeighingTicketDao
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity

@Database(
    entities = [
        WeighingTicketEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val weighingTicketDao: WeighingTicketDao
}