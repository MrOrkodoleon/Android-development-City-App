package com.example.cityapp.data.repository

import com.example.cityapp.data.models.Category
import com.example.cityapp.data.models.Recommendation
import kotlinx.coroutines.flow.Flow

interface CityDataRepository {
    fun getAllCategories(): Flow<List<Category>>
    fun getCategoryById(categoryId: Long): Flow<Category?>
    fun getRecommendationsForCategory(categoryId: Long): Flow<List<Recommendation>>
    fun getRecommendationById(recommendationId: Long): Flow<Recommendation?>
}