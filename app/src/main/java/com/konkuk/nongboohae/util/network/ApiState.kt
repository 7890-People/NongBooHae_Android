package com.konkuk.nongboohae.util.network

sealed class ApiState<T>(
) {
    class Success<T>(val data: T?, val message: String? = null) : ApiState<T>()
    class Error<T>(val errorResponse: ErrorResponse?) : ApiState<T>()
    class Loading<T> : ApiState<T>()

    fun <R> byState(
        onSuccess: (T) -> (R?),
        onFailure: (ErrorResponse?) -> (Unit) = {},
        onLoading: () -> (Unit) = {}
    ): R? {
        when (this) {
            is Success -> {
                return this.data?.let(onSuccess)
            }
            is Error -> {
                onFailure(errorResponse)
            }
            is Loading -> {
                onLoading.invoke()
            }
        }
        return null
    }
}