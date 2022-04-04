package com.compose.testing.pagination

sealed class Result<T> {
    class Success<T>(val data : T? = null, val message: String? = null) : Result<T>()
    class Error<T>(val throwable: Throwable? = null, val message : String? = null): Result<T>()
}
