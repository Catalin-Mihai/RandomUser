package com.catalin.randomuser.data.repository.model

import java.util.Calendar
import kotlin.random.Random
import kotlin.random.nextLong

data class User(
    val gender: Gender,
    val name: Name,
    val location: Location,
    val email: String,
    val avatar: Avatar,
    val id: Identifier,
    val age: Int
) {
    val lastMessageSentTimeTs: Long
        get() {
            val endTs = Calendar.getInstance().timeInMillis
            val startTs = endTs - 1000L * 60 * 60 * 24 * 365

            //Generate a random value for mock purpose
            return Random.nextLong(startTs..endTs)
        }

    val lastMessageHasAttachment: Boolean
        //Generate a random value for mock purpose
        get() = Random.nextBoolean()
}

data class Name(
    val title: String,
    val first: String,
    val last: String
) {
    val fullName
        get() = "$first $last"
}

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

data class Identifier(
    val name: String? = null,
    val value: String? = null
)

enum class Gender {
    MALE,
    FEMALE
}
