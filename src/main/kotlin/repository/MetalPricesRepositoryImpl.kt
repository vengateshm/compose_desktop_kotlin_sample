package repository

import common.Result
import data.network.MetalPricesApi
import data.network.response.toMetalPrice
import models.MetalPrice
import models.errorMessage

class MetalPricesRepositoryImpl(private val api: MetalPricesApi) : MetalPricesRepository {
    override suspend fun getMetalPrice(): Result<MetalPrice> {
        val response = api.getMetalPrice()

        return response.fold(
            ifLeft = { apiError -> Result.Error(Throwable(apiError.errorMessage())) },
            ifRight = { metalPricesResponse -> Result.Success(metalPricesResponse.toMetalPrice()) }
        )
    }
}