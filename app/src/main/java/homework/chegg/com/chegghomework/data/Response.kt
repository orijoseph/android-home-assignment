package homework.chegg.com.chegghomework.data

sealed class Response<out T>(
        val data: T? = null,
        val error: Throwable? = null
) {
    class Success<out T>(data: T) : Response<T>(data = data)
    class Error<out T>(error: Throwable, data: T? = null) : Response<T>(data, error)
}