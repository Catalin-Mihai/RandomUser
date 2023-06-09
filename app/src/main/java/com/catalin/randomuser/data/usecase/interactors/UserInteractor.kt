package com.catalin.randomuser.data.usecase.interactors

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.catalin.randomuser.data.repository.data.UsersPagingSource
import com.catalin.randomuser.data.repository.repos.UserRepository
import com.catalin.randomuser.data.utils.genericHandle

//For simplicity, repo models are reused on the usecase layer.

class UserInteractor(private val userRepository: UserRepository) {
    suspend fun getAllUsers() =
        genericHandle { userRepository.getUsers(MAX_RESULTS_LOADED, RANDOM_SEED) }

    fun getUsersAsPagingFlow() = Pager(config = PagingConfig(
        pageSize = PAGING_PAGE_SIZE,
        initialLoadSize = PAGING_INITIAL_LOAD_SIZE,
        prefetchDistance = PAGING_PREFETCH_DISTANCE
    ), pagingSourceFactory = {
        UsersPagingSource(maxPagesLoaded = MAX_PAGES_LOADED) { pageKey: Int, loadSize: Int ->
            userRepository.getUsers(pageKey, loadSize, RANDOM_SEED)
        }
    }).flow

    companion object {
        private const val MAX_RESULTS_LOADED = 100
        private const val RANDOM_SEED = "abc"

        private const val MAX_PAGES_LOADED = 3
        private const val PAGING_PAGE_SIZE = 20
        private const val PAGING_INITIAL_LOAD_SIZE = PAGING_PAGE_SIZE
        private const val PAGING_PREFETCH_DISTANCE = 3
    }
}