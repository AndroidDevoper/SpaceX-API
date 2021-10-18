package com.example.spacex_api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksRequest(
    @Json(name = "patch") val patch: PatchRequest
)
