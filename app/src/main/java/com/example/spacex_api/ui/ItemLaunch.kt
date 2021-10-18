package com.example.spacex_api.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemLaunch (
    val name: String,
    val date_unix: Long,
    val date: String,
    val details: String?,
    val url_img: String?,
        ):Parcelable
