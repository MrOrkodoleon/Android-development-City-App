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
import com.example.cityapp.data.models.Category
import com.example.cityapp.ui.theme.CityAppTheme

@Composable
fun CategoryScreen(
    categories: List<Category>,
    onCategoryClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories, key = { it.id }) { category ->
            CategoryListItem(
                category = category,
                onCategoryClick = { onCategoryClick(category.id) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListItem(
    category: Category,
    onCategoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onCategoryClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = category.imageResourceId),
                contentDescription = category.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(7.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CityAppTheme {
        val previewCategories = listOf(
            Category(1, "Parks", R.drawable.ic_category_park, emptyList()),
            Category(2, "Museums", R.drawable.ic_category_museum, emptyList())
        )
        CategoryScreen(categories = previewCategories, onCategoryClick = {})
    }
}