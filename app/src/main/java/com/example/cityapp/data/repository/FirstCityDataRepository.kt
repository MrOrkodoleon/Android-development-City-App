package com.example.cityapp.data.repository

import com.example.cityapp.data.DataSource
import com.example.cityapp.data.models.Category
import com.example.cityapp.data.models.Recommendation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirstCityDataRepository @Inject constructor(): CityDataRepository {
    override fun getAllCategories(): Flow<List<Category>> = flow {
        emit(DataSource.getAllCategories())
    }

    override fun getCategoryById(categoryId: Long): Flow<Category?> = flow {
        emit(DataSource.getCategoryById(categoryId))
    }

    override fun getRecommendationsForCategory(categoryId: Long): Flow<List<Recommendation>> = flow {
        emit(DataSource.getRecommendationsForCategory(categoryId))
    }

    override fun getRecommendationById(recommendationId: Long): Flow<Recommendation?> = flow {
        emit(DataSource.getRecommendationById(recommendationId))
    }
}