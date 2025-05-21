package com.example.cityapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cityapp.R
import com.example.cityapp.data.models.Recommendation
import com.example.cityapp.ui.theme.CityAppTheme

@Composable
fun RecommendationsScreen(
    recommendations: List<Recommendation>,
    onRecommendationClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    if (recommendations.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No recommendations found for this category.")
        }
        return
    }
    LazyColumn(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(recommendations, key = { it.id }) { recommendation ->
            RecommendationListItem(
                recommendation = recommendation,
                onRecommendationClick = { onRecommendationClick(recommendation.id) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationListItem(
    recommendation: Recommendation,
    onRecommendationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onRecommendationClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = recommendation.imageResourceId),
                contentDescription = recommendation.name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Fit
            )
            Column {
                Text(
                    text = recommendation.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = recommendation.address,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationsScreenPreview() {
    CityAppTheme {
        val previewRecommendations = listOf(
            Recommendation(101, 1, "Cool Place 1", R.drawable.kalashnikov_museum, "Desc 1", "123 Main St", "9-5"),
            Recommendation(102, 1, "Fun Spot 2", R.drawable.izhevsk_zoo, "Desc 2", "456 Oak Ave", "10-6")
        )
        RecommendationsScreen(recommendations = previewRecommendations, onRecommendationClick = {})
    }
}