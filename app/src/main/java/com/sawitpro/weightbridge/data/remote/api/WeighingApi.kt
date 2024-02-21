package com.sawitpro.weightbridge.data.remote.api

import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.data.model.dto.WeighingTicketDto
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface WeighingApi {

    @GET("posts.json")
    suspend fun getWeighingList(): Response<Map<String, WeighingTicketDto>>

    @POST("posts.json")
    suspend fun setWeighingList(request: RequestCreateEditWeighingTicketEntity): Response<SetWeighingResponseDto>

    @PUT("posts.json")
    suspend fun updateWeighingList(request: RequestCreateEditWeighingTicketEntity): Response<WeighingTicketDto>
}