package io.wwdaigo.domain.commons

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error<out T>(val throwable: Throwable): Result<T>()
}