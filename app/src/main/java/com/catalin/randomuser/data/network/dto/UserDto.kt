package com.catalin.randomuser.data.network.dto

import com.google.gson.annotations.SerializedName;

data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
    val email: String,
    @SerializedName("picture")
    val avatar: AvatarDto,
    val id: IdentifierDto,
    val dob: DobDto
)

data class NameDto(
    val title: String,
    val first: String,
    val last: String
)

data class AvatarDto(
    @SerializedName("thumbnail")
    val url: String
)

data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val country: String
)

data class StreetDto(
    val number: Int,
    val name: String
)

data class IdentifierDto(
    val name: String?,
    val value: String?
)

data class DobDto(
    val date: String,
    val age: Int
)