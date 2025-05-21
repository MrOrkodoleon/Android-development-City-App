package com.example.cityapp.ui.navigation

interface AppDestination {
    val route: String
}

object CategoriesScreenDest : AppDestination {
    override val route = "categories_overview"
}

object RecommendationsScreenDest : AppDestination {
    override val route = "recommendations_list"
    const val categoryIdArg = "categoryId"
    val routeWithArgs = "$route/{$categoryIdArg}"
}

object RecommendationDetailScreenDest : AppDestination {
    override val route = "recommendation_detail"
    const val recommendationIdArg = "recommendationId"
    val routeWithArgs = "$route/{$recommendationIdArg}"
}