package com.catalin.randomuser.data.repository.repos

import com.catalin.randomuser.data.network.service.ApiService
import com.catalin.randomuser.data.repository.utils.genericHandle
import com.catalin.randomuser.data.repository.utils.toRepoModel

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers() =
        genericHandle { apiService.fetchUsers(10).users.map { it.toRepoModel() } }

}