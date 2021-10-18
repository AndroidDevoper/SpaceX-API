package com.example.spacex_api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatchRequest(
    @Json(name = "small") val url_img: String?
)
