package com.catalin.randomuser.data.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> genericHandle(call: suspend () -> T) = flow {
    emit(Result.Loading)
    kotlin.runCatching {
        call()
    }.onSuccess {
        emit(Result.Success(it))
    }.onFailure {
        emit(Result.Error(it))
    }
}.flowOn(Dispatchers.IO)

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val error: Throwable? = null) : Result<Nothing>()
}