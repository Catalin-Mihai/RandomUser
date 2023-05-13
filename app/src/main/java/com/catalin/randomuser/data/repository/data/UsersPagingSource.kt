package com.catalin.randomuser.data.repository.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.catalin.randomuser.data.repository.model.User

class UsersPagingSource(
    private val maxPagesLoaded: Int,
    private val fetchUsers: suspend (pageKey: Int, loadSize: Int) -> List<User>
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageKey = params.key ?: 1
        val loadSize = params.loadSize

        if (pageKey > maxPagesLoaded) {
            return LoadResult.Error(Exception("Maximum number of fetched pages reached!"))
        }

        return kotlin.runCatching {
            fetchUsers(pageKey, loadSize)
        }.map {
            toLoadResult(it.also {
                Log.d(
                    UsersPagingSource::class.simpleName,
                    "Served page: $pageKey. Fetched ${it.size} results!"
                )
            }, position = pageKey, loadSize = loadSize)
        }.getOrElse { LoadResult.Error(it) }
    }

    private fun toLoadResult(
        data: List<User>, position: Int, loadSize: Int
    ): LoadResult<Int, User> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (data.size < loadSize) null else position + 1
        )
    }
}
