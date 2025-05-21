package com.example.cityapp.data.models

import androidx.annotation.DrawableRes

data class Recommendation(
    val id: Long,
    val categoryId: Long,
    val name: String,
    @DrawableRes val imageResourceId: Int,
    val description: String,
    val address: String,
    val workingHours: String? = null
)
