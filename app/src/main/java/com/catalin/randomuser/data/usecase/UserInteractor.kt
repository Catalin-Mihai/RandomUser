package com.catalin.randomuser.data.usecase

import com.catalin.randomuser.data.repository.repos.UserRepository

//For simplicity, repo models are reused on the usecase layer.

class UserInteractor(private val userRepository: UserRepository) {
    suspend fun getUsers() = userRepository.getUsers()
}