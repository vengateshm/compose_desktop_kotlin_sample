package data.network

import arrow.core.Either
import data.network.response.MetalPricesResponse
import models.ApiError
import retrofit2.http.GET
import retrofit2.http.Header

interface MetalPricesApi {
    @GET("get_metal_prices")
    suspend fun getMetalPrice(
        @Header("x-rapidapi-host") host: String = "gold-price-live.p.rapidapi.com",
        @Header("x-rapidapi-key") key: String = "ada30abe7dmsh488fb7b629c9927p13e78cjsncdf31e6870d1"
    ): Either<ApiError, MetalPricesResponse>

    companion object {
        const val BASE_URL = "https://gold-price-live.p.rapidapi.com/"
    }
}