package com.catalin.randomuser.data.network.service

import com.catalin.randomuser.data.network.dto.FetchUsersResponseDto
import retrofit2.http.GET

interface ApiService {

    @GET(".")
    suspend fun fetchUsers(): FetchUsersResponseDto

}