package com.corps.fuelmate.network

import com.corps.fuelmate.network.fuel.FuelResponse
import retrofit2.http.GET
import retrofit2.http.Query

/* This API RESTful exposes the prices of the Dominican Republic provided fuels by the MICM.*/
interface FuelApi {

    @GET("v1/fuels")
    suspend fun getFuelPrices(@Query("date") date: String): FuelResponse?
}


//Base URl   https://api.digital.gob.do/
