package com.example.cityapp.di

import com.example.cityapp.data.repository.CityDataRepository
import com.example.cityapp.data.repository.FirstCityDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCityDataRepository(
        firstCityDataRepository: FirstCityDataRepository
    ): CityDataRepository
}