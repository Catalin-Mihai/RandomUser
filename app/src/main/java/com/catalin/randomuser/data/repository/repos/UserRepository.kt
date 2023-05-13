package com.catalin.randomuser.data.repository.repos

import com.catalin.randomuser.data.network.service.ApiService
import com.catalin.randomuser.data.utils.toRepoModel

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers(resultsNumber: Int, seed: String) =
        apiService.fetchAllUsers(resultsNumber, seed).users.map { it.toRepoModel() }

    suspend fun getUsers(page: Int, resultsNumber: Int, seed: String) =
        apiService.fetchUsersPage(page, resultsNumber, seed).users.map { it.toRepoModel() }
}