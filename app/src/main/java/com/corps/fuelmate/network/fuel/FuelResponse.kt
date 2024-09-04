package com.corps.fuelmate.network.fuel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FuelResponse(
    @Json(name = "data") val data: List<DataResponse>,
    @Json(name = "meta") val meta: MetaResponse,
    @Json(name = "valid") val valid: Boolean
)
