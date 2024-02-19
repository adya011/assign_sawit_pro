package com.sawitpro.weightbridge.data.remote.api

import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.data.model.dto.TruckDataDto
import com.sawitpro.weightbridge.data.model.dto.WeighingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface WeighingApi {
    /*@GET("get.json")
    suspend fun getWeighingList(): Response<List<TruckDataDto>>*/

    @GET("posts.json")
    suspend fun getWeighingList(): Response<Map<String, TruckDataDto>>

    @POST("posts.json")
    suspend fun setWeighingList(): Response<SetWeighingResponseDto>

    @PUT("posts.json")
    suspend fun updateWeighingList(): Response<Map<String, TruckDataDto>>
}