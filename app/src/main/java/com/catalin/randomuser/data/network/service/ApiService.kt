package com.catalin.randomuser.data.network.service

import com.catalin.randomuser.data.network.dto.FetchUsersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun fetchAllUsers(
        @Query("results") resultsNumber: Int, @Query("seed") seed: String
    ): FetchUsersResponseDto

    @GET("api/")
    suspend fun fetchUsersPage(
        @Query("page") pageNumber: Int,
        @Query("results") resultsNumber: Int,
        @Query("seed") seed: String
    ): FetchUsersResponseDto
}