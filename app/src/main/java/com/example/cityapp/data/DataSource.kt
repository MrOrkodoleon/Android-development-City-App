package com.example.cityapp.data

import com.example.cityapp.R
import com.example.cityapp.data.models.Category
import com.example.cityapp.data.models.Recommendation


object DataSource {

    val izhevskCategories = listOf(
        Category(
            id = 1L,
            name = "Museums & Culture",
            imageResourceId = R.drawable.ic_category_museum,
            recommendations = listOf(
                Recommendation(
                    id = 101L,
                    categoryId = 1L,
                    name = "Kalashnikov Museum and Exhibition Complex",
                    imageResourceId = R.drawable.kalashnikov_museum,
                    description = "Explore the history of Mikhail Kalashnikov and his iconic AK-47 rifle. Features interactive exhibits and a shooting range.",
                    address = "ul. Borodina, 19, Izhevsk, Udmurt Republic, 426000",
                    workingHours = "Tue-Sun: 10:00 AM - 6:00 PM"
                ),
                Recommendation(
                    id = 102L,
                    categoryId = 1L,
                    name = "Udmurt Republican Museum of Fine Arts",
                    imageResourceId = R.drawable.udmurt_fine_arts_museum,
                    description = "Home to a diverse collection of Russian and Udmurt art, including paintings, sculptures, and decorative arts.",
                    address = "ul. Kirova, 128, Izhevsk, Udmurt Republic, 426034",
                    workingHours = "Wed-Sun: 10:00 AM - 5:00 PM"
                ),
                Recommendation(
                    id = 103L,
                    categoryId = 1L,
                    name = "National Museum of the Udmurt Republic",
                    imageResourceId = R.drawable.udmurt_national_museum,
                    description = "Discover the rich history, culture, and ethnography of the Udmurt people and the region.",
                    address = "ul. Kommunarov, 287, Izhevsk, Udmurt Republic, 426004",
                    workingHours = "Tue-Sat: 9:00 AM - 5:00 PM"
                )
            ),
        ),
        Category(
            id = 2L,
            name = "Parks & Recreation",
            imageResourceId = R.drawable.ic_category_park,
            recommendations = listOf(
                Recommendation(
                    id = 201L,
                    categoryId = 2L,
                    name = "Kirov Park",
                    imageResourceId = R.drawable.kirov_park,
                    description = "A large central park with beautiful walking paths, amusement rides, a pond, and spaces for relaxation and events.",
                    address = "Central Izhevsk, near Pushkinskaya St.",
                    workingHours = "Open 24/7 (attractions may have specific hours)"
                ),
                Recommendation(
                    id = 202L,
                    categoryId = 2L,
                    name = "Izhevsk Zoo (Udmurtia Zoo)",
                    imageResourceId = R.drawable.izhevsk_zoo,
                    description = "One of the largest zoos in the Volga region, featuring a wide variety of animals from around the world.",
                    address = "ul. Kirova, 8, Izhevsk, Udmurt Republic, 426033",
                    workingHours = "Daily: 9:00 AM - 7:00 PM (Summer hours may vary)"
                ),
                Recommendation(
                    id = 203L,
                    categoryId = 2L,
                    name = "Cosmonauts Park",
                    imageResourceId = R.drawable.cosmonauts_park,
                    description = "A thematic park dedicated to space exploration, with green areas and monuments.",
                    address = "Votkinskoye Shosse, 120a, Izhevsk",
                )
            )
        ),
        Category(
            id = 3L,
            name = "Food & Drink",
            imageResourceId = R.drawable.ic_category_food,
            recommendations = listOf(
                Recommendation(
                    id = 301L,
                    categoryId = 3L,
                    name = "Mama Pizza",
                    imageResourceId = R.drawable.mama_pizza,
                    description = "Popular local chain for pizza and Italian-inspired dishes. Good for families and casual dining.",
                    address = "Multiple locations, e.g., ul. Krasnoarmeyskaya, 127",
                    workingHours = "Daily: 10:00 AM - 11:00 PM"
                ),
                Recommendation(
                    id = 302L,
                    categoryId = 3L,
                    name = "Tasty Coffee",
                    imageResourceId = R.drawable.tasty_coffee,
                    description = "A cozy coffee shop chain, great for coffee, pastries, and light snacks.",
                    address = "Multiple locations, e.g., ul. Sovetskaya, 22",
                    workingHours = "Daily: 7:30 AM - 9:30 PM"
                ),
                Recommendation(
                    id = 303L,
                    categoryId = 3L,
                    name = "Goryachie Pelmeni (Горячие Пельмени)",
                    imageResourceId = R.drawable.pelmeni_place,
                    description = "Experience traditional Russian pelmeni (dumplings) with various fillings and broths.",
                    address = "ul. Lenina, 30, Izhevsk",
                    workingHours = "Mon-Sat: 11:00 AM - 8:00 PM"
                )
            )
        ),
        Category(
            id = 4L,
            name = "Theatres & Entertainment",
            imageResourceId = R.drawable.ic_category_theatre,
            recommendations = listOf(
                Recommendation(
                    id = 401L,
                    categoryId = 4L,
                    name = "State Opera and Ballet Theatre of the Udmurt Republic",
                    imageResourceId = R.drawable.opera_ballet_theatre,
                    description = "Hosts classical opera and ballet performances, as well as operettas and musical comedies.",
                    address = "ul. Pushkinskaya, 221, Izhevsk, Udmurt Republic, 426008",
                    workingHours = "Check schedule for performance times"
                ),
                Recommendation(
                    id = 402L,
                    categoryId = 4L,
                    name = "State National Theatre of the Udmurt Republic",
                    imageResourceId = R.drawable.udmurt_national_theatre,
                    description = "Presents plays and performances primarily in the Udmurt language, showcasing national culture.",
                    address = "ul. Maksima Gorkogo, 73, Izhevsk, Udmurt Republic, 426057",
                    workingHours = "Check schedule for performance times"
                )
            )
        )
    )

    fun getAllRecommendations(): List<Recommendation> {
        return izhevskCategories.flatMap { it.recommendations }
    }

    fun getRecommendationsForCategory(categoryId: Long): List<Recommendation> {
        return izhevskCategories.find { it.id == categoryId }?.recommendations ?: emptyList()
    }

    fun getRecommendationById(recommendationId: Long): Recommendation? {
        return getAllRecommendations().find { it.id == recommendationId }
    }

    fun getAllCategories(): List<Category> = izhevskCategories

    fun getCategoryById(categoryId: Long): Category? {
        return izhevskCategories.find { it.id == categoryId }
    }
}