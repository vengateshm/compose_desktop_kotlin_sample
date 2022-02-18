package data.network.response

import models.MetalPrice

data class MetalPricesResponse(val gold: Double, val silver: Double)

fun MetalPricesResponse.toMetalPrice() = MetalPrice(
    gold = this.gold,
    silver = this.silver
)
