package models

sealed class ApiError {
    data class HttpError(val code: Int, val body: String) : ApiError() // Error codes 4xx-5xx
    data class NetworkError(val throwable: Throwable) : ApiError() // IOException and similar others
    data class UnknownApiError(val throwable: Throwable) : ApiError()
}

fun ApiError.errorMessage(): String {
    return when (this) {
        is ApiError.HttpError -> "Server Error"
        is ApiError.NetworkError -> this.throwable.message ?: "Network Error"
        is ApiError.UnknownApiError -> this.throwable.message ?: "Error occurred"
    }
}
