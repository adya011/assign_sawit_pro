package com.sawitpro.weightbridge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity

@Dao
interface WeighingTicketDao {
    @Query("SELECT * FROM weighing_ticket")
    fun getWeighingTicketList(): List<WeighingTicketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeighingTicket(weighingTicket: List<WeighingTicketEntity>)
}