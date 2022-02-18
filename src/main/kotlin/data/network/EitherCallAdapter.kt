package data.network

import arrow.core.Either
import models.ApiError
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class EitherCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<Either<ApiError, R>>> {
    override fun responseType(): Type {
        return successType
    }

    override fun adapt(call: Call<R>): Call<Either<ApiError, R>> {
        return EitherCall(call, successType)
    }
}