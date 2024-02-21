package com.sawitpro.weightbridge.data.remote.api

import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.data.model.dto.WeighingTicketDto
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WeighingApi {

    @GET("posts.json")
    suspend fun getWeighingList(): Response<Map<String, WeighingTicketDto>>

    @POST("posts.json")
    suspend fun setWeighingList(request: RequestCreateEditWeighingTicketEntity): Response<SetWeighingResponseDto>

    @PUT("posts/{uId}.json")
    suspend fun updateWeighingList(
        @Path("uId") uId: String,
        request: RequestCreateEditWeighingTicketEntity
    ): Response<WeighingTicketDto>
}