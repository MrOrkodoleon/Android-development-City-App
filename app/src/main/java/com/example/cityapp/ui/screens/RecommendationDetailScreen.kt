package com.example.cityapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cityapp.R
import com.example.cityapp.data.models.Recommendation
import com.example.cityapp.ui.theme.CityAppTheme

@Composable
fun RecommendationDetailScreen(
    recommendation: Recommendation?,
    modifier: Modifier = Modifier
) {
    if (recommendation == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Recommendation details not found.")
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = recommendation.imageResourceId),
            contentDescription = recommendation.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Larger image for detail
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = recommendation.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = recommendation.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.address_label, recommendation.address),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        recommendation.workingHours?.let {
            Text(
                text = stringResource(R.string.working_hours_label, it),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationDetailScreenPreview() {
    CityAppTheme {
        val previewRecommendation = Recommendation(
            id = 101L,
            categoryId = 1L,
            name = "Kalashnikov Museum and Exhibition Complex",
            imageResourceId = R.drawable.kalashnikov_museum,
            description = "Explore the history of Mikhail Kalashnikov and his iconic AK-47 rifle. Features interactive exhibits and a shooting range.",
            address = "ul. Borodina, 19, Izhevsk, Udmurt Republic, 426000",
            workingHours = "Tue-Sun: 10:00 AM - 6:00 PM"
        )
        RecommendationDetailScreen(recommendation = previewRecommendation)
    }
}