package com.catalin.randomuser.data.repository.utils

import com.catalin.randomuser.data.network.dto.AvatarDto
import com.catalin.randomuser.data.network.dto.LocationDto
import com.catalin.randomuser.data.network.dto.NameDto
import com.catalin.randomuser.data.network.dto.StreetDto
import com.catalin.randomuser.data.network.dto.UserDto
import com.catalin.randomuser.data.repository.model.Avatar
import com.catalin.randomuser.data.repository.model.Gender
import com.catalin.randomuser.data.repository.model.Location
import com.catalin.randomuser.data.repository.model.Name
import com.catalin.randomuser.data.repository.model.Street
import com.catalin.randomuser.data.repository.model.User

fun UserDto.toRepoModel() = User(
    gender = Gender.valueOf(gender),
    name = name.toRepoModel(),
    location = location.toRepoModel(),
    email = email,
    avatar = avatar.toRepoModel()
)

fun NameDto.toRepoModel() = Name(
    title = title, first = first, last = last
)

fun LocationDto.toRepoModel() = Location(
    street = street.toRepoModel(), city = city, state = state, country = country
)

fun StreetDto.toRepoModel() = Street(
    number = number, name = name
)

fun AvatarDto.toRepoModel() = Avatar(
    url = url
)

