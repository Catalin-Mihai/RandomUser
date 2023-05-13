package com.catalin.randomuser.data.network.dto

import com.google.gson.annotations.SerializedName

data class FetchUsersResponseDto(
    @SerializedName("results")
    val users: List<UserDto>
)