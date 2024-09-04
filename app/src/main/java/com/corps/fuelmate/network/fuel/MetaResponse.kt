package com.corps.fuelmate.network.fuel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaResponse(
    @Json(name = "source") val source: String,
    @Json(name = "week") val week: Int,
    @Json(name = "year") val year: Int,
    @Json(name = "updatedAt") val updatedAt: String
)
