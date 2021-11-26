package com.jedun.cleanpixabay.data.repository


import com.jedun.cleanpixabay.data.cache.CacheResult
import com.jedun.cleanpixabay.data.network.ApiResult
import com.jedun.cleanpixabay.utils.Resource
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException

fun <T> safeApiCall(
    apiCall: () -> T?
): ApiResult<T?> {

    return try {
        ApiResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {

        when (throwable) {
            is TimeoutCancellationException -> {
                val code = 408
                ApiResult.GenericError(code, "")
            }
            is IOException -> {
                ApiResult.NetworkError
            }
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable)
                ApiResult.GenericError(
                    code,
                    errorResponse
                )
            }
            else -> ApiResult.GenericError(null, "Error Unknown")
        }

    }
}


fun <T> safeCacheCall(
    cacheCall: () -> T?
): CacheResult<T?> {

    return try {
        CacheResult.Success(cacheCall.invoke())
    } catch (throwable: Throwable) {

        throwable.printStackTrace()

        when (throwable) {
            is TimeoutCancellationException -> {
                CacheResult.GenericError("")
            }
            else -> CacheResult.GenericError("Error Unknown")
        }
    }
}


private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        "Unknown Error"
    }
}


fun <T> apiResultToResource(apiResult: ApiResult<T>): Resource<T> {

    return when (apiResult) {
        is ApiResult.GenericError -> {
            Resource.Error("Error ${apiResult.code} : ${apiResult.errorMessage}")
        }
        ApiResult.NetworkError -> {
            Resource.Error("Network Error")
        }
        is ApiResult.Success -> {
            Resource.Success(apiResult.value)
        }
    }
}


