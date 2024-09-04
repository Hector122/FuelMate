package com.corps.fuelmate

import com.corps.fuelmate.fuel.Fuel
import com.corps.fuelmate.network.FuelApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FuelRepository @Inject constructor(
    private val fuelApi: FuelApi
) {
    suspend fun getFuelPriceFlow(date: String): Flow<List<Fuel>> = flow {
        emit(
            fuelApi.getFuelPrices(date)?.data?.map {
                Fuel(
                    id = it.id,
                    name = it.name,
                    price = it.price,
                    code = it.code,
                    currency = it.currency,
                    date = it.date
                )
            } ?: emptyList()
        )
    }

    suspend fun getFuelPrices(date: String): List<Fuel> {
        return fuelApi.getFuelPrices(date)?.data?.map {
            Fuel(
                id = it.id,
                name = it.name,
                price = it.price,
                code = it.code,
                currency = it.currency,
                date = it.date
            )
        } ?: emptyList()
    }

}
