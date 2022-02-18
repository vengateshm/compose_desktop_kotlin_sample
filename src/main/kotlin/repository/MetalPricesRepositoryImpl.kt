package repository

import common.Result
import data.network.MetalPricesApi
import data.network.response.toMetalPrice
import models.MetalPrice

class MetalPricesRepositoryImpl(private val api: MetalPricesApi) : MetalPricesRepository {
    override suspend fun getMetalPrice(): Result<MetalPrice> {
        return try {
            val response = api.getMetalPrice()
            Result.Success(response.toMetalPrice())
        } catch (e: Exception) {
            //Result.Error(e)
            Result.Success(MetalPrice(0.0, 0.0))
        }
    }
}