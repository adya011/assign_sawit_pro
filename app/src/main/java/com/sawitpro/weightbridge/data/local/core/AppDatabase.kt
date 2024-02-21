package com.sawitpro.weightbridge.data.local.core

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [

    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
}