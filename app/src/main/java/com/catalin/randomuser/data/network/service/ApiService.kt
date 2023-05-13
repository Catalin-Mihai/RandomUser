package com.catalin.randomuser.data.network.service

import com.catalin.randomuser.data.network.dto.FetchUsersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun fetchUsers(@Query("results") resultsNumber: Int): FetchUsersResponseDto

}