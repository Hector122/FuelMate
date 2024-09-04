package com.corps.fuelmate.network.fuel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "price") val price: String,
    @Json(name = "code") val code: String,
    @Json(name = "date") val date: String,
    @Json(name = "currency") val currency: String
)
