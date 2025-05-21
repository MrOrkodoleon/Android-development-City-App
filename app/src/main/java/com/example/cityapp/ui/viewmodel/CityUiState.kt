package com.example.cityapp.ui.viewmodel

import com.example.cityapp.data.models.Category
import com.example.cityapp.data.models.Recommendation

data class CityUiState(
    val categories: List<Category> = emptyList(),
    val currentRecommendations: List<Recommendation> = emptyList(),
    val currentSelectedRecommendation: Recommendation? = null,
    val selectedCategoryName: String? = null,
    val isShowingHomepage: Boolean = true
)
