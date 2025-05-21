package com.example.cityapp.data.models

import androidx.annotation.DrawableRes

data class Category(
    val id: Long,
    val name: String,
    @DrawableRes val imageResourceId: Int,
    val recommendations: List<Recommendation>
)
