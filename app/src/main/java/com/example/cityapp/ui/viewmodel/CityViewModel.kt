package com.example.cityapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityapp.data.models.Category
import com.example.cityapp.data.models.Recommendation
import com.example.cityapp.data.repository.CityDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val cityDataRepository: CityDataRepository) : ViewModel() {

    private val _selectedCategoryId = MutableStateFlow<Long?>(null)
    private val _selectedRecommendationId = MutableStateFlow<Long?>(null)

    private val _categoriesFlow: StateFlow<List<Category>> = cityDataRepository.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    private val _recommendationsForSelectedCategoryFlow: StateFlow<List<Recommendation>> =
        _selectedCategoryId.combine(cityDataRepository.getAllCategories()) { categoryId, categories ->
            if (categoryId != null) {
                categories.find { it.id == categoryId }?.recommendations ?: emptyList()
            } else {
                emptyList()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())


    private val _detailForSelectedRecommendationFlow: StateFlow<Recommendation?> =
        _selectedRecommendationId.combine(cityDataRepository.getAllCategories()) { recId, categories ->
            if (recId != null) {
                categories.flatMap { it.recommendations }.find { it.id == recId }
            } else {
                null
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), null)


    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                _categoriesFlow,
                _recommendationsForSelectedCategoryFlow,
                _detailForSelectedRecommendationFlow,
                _selectedCategoryId,
                _selectedRecommendationId
            ) { categories, currentRecommendations, currentDetail, catId, recId ->
                val selectedCategory = categories.find { it.id == catId }
                CityUiState(
                    categories = categories,
                    currentRecommendations = currentRecommendations,
                    currentSelectedRecommendation = currentDetail,
                    selectedCategoryName = selectedCategory?.name,
                    isShowingHomepage = catId == null
                )
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    fun selectCategory(categoryId: Long) {
        _selectedCategoryId.value = categoryId
        _selectedRecommendationId.value = null
    }

    fun selectRecommendation(recommendationId: Long) {
        _selectedRecommendationId.value = recommendationId
    }
}