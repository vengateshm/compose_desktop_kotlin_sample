package repository

import common.Result
import models.MetalPrice

interface MetalPricesRepository {
    suspend fun getMetalPrice(): Result<MetalPrice>
}