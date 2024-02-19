package com.sawitpro.weightbridge.data.core

sealed class DataResult<T> {
    data class Loading<T>(val isActive: Boolean = false) : DataResult<T>()
    data class Success<T>(val body: T) : DataResult<T>()
    data class Error<T>(val errorMessage: String, val code: Int) : DataResult<T>()
}