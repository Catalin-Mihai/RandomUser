package com.catalin.randomuser.data.repository.model

data class User(
    val gender: Gender,
    val name: Name,
    val location: Location,
    val email: String,
    val avatar: Avatar
)

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Avatar(
    val url: String
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String
)

data class Street(
    val number: Int,
    val name: String
)

enum class Gender {
    MALE,
    FEMALE
}
