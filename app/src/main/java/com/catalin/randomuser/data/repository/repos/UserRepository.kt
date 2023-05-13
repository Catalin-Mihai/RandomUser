package com.catalin.randomuser.data.repository.repos

import com.catalin.randomuser.data.network.service.ApiService
import com.catalin.randomuser.data.repository.utils.toRepoModel

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.fetchUsers().users.map { it.toRepoModel() }
}