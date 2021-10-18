package com.example.spacex_api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchRequest (
    @Json(name = "links") val links: LinksRequest,
    @Json(name = "success") val success: Boolean?,
    @Json(name = "details") val details: String?,
    @Json(name = "name") val name: String,
    @Json(name = "date_unix") val date: Int
        )


