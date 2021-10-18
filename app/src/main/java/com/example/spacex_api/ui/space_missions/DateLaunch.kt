package com.example.spacex_api.ui.space_missions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

class DateLaunch {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun isIncludedInTheAnnualRange(date: Long, with: Int, before: Int): Boolean {
            val dateFormat = SimpleDateFormat("yyyy")
            val year = dateFormat.format(date*1000).toInt()
            return year in with..before
        }

        @SuppressLint("SimpleDateFormat")
        fun format(date: Long): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            return dateFormat.format(date*1000)
        }
    }
}