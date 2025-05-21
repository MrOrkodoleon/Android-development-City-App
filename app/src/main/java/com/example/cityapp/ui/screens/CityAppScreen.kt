package com.example.cityapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cityapp.R
import com.example.cityapp.ui.navigation.CategoriesScreenDest
import com.example.cityapp.ui.navigation.RecommendationDetailScreenDest
import com.example.cityapp.ui.navigation.RecommendationsScreenDest
import com.example.cityapp.ui.viewmodel.CityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppScreen(
    navController: NavHostController = rememberNavController(),
    cityViewModel: CityViewModel = hiltViewModel()
) {
    val uiState by cityViewModel.uiState.collectAsStateWithLifecycle()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreenTitle = when (backStackEntry?.destination?.route) {
        CategoriesScreenDest.route -> stringResource(R.string.categories_title)
        RecommendationsScreenDest.routeWithArgs -> uiState.selectedCategoryName
            ?: stringResource(R.string.recommendations_title)

        RecommendationDetailScreenDest.routeWithArgs -> uiState.currentSelectedRecommendation?.name
            ?: ""

        else -> stringResource(R.string.app_name)
    }
    val canNavigateBack = navController.previousBackStackEntry != null &&
            backStackEntry?.destination?.route != CategoriesScreenDest.route


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentScreenTitle) },
                navigationIcon = {
                    if (canNavigateBack) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.content_description_back_button)
                            )
                        }
                    }
                },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CategoriesScreenDest.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = CategoriesScreenDest.route) {
                // Ensure ViewModel resets to category view state if navigated here directly
                // cityViewModel.navigateToCategoriesScreen() // ViewModel already handles this via isShowingHomepage
                CategoryScreen(
                    categories = uiState.categories,
                    onCategoryClick = { categoryId ->
                        cityViewModel.selectCategory(categoryId)
                        navController.navigate("${RecommendationsScreenDest.route}/${categoryId}")
                    }
                )
            }

            composable(
                route = RecommendationsScreenDest.routeWithArgs,
                arguments = listOf(navArgument(RecommendationsScreenDest.categoryIdArg) {
                    type = NavType.LongType
                })
            ) { backStackEntryArg ->
                val categoryId =
                    backStackEntryArg.arguments?.getLong(RecommendationsScreenDest.categoryIdArg)
                // If categoryId is available, ensure ViewModel is aware (e.g. for deep links or process death)
                LaunchedEffect(categoryId) {
                    categoryId?.let { cityViewModel.selectCategory(it) }
                }

                RecommendationsScreen(
                    recommendations = uiState.currentRecommendations,
                    onRecommendationClick = { recommendationId ->
                        cityViewModel.selectRecommendation(recommendationId) // Update ViewModel state
                        navController.navigate("${RecommendationDetailScreenDest.route}/${recommendationId}")
                    }
                )
            }

            composable(
                route = RecommendationDetailScreenDest.routeWithArgs,
                arguments = listOf(navArgument(RecommendationDetailScreenDest.recommendationIdArg) {
                    type = NavType.LongType
                })
            ) { backStackEntryArg ->
                val recommendationId = backStackEntryArg.arguments?.getLong(
                    RecommendationDetailScreenDest.recommendationIdArg
                )
                // If recommendationId is available, ensure ViewModel is aware
                LaunchedEffect(recommendationId) {
                    recommendationId?.let { cityViewModel.selectRecommendation(it) }
                }
                RecommendationDetailScreen(
                    recommendation = uiState.currentSelectedRecommendation,
                )
            }
        }
    }
}