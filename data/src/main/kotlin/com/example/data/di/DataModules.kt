package com.example.data.di

import com.example.data.repository.PeopleRepositoryImpl
import com.example.domain.repository.PeopleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindPeopleRepository(
        peopleRepositoryImpl: PeopleRepositoryImpl
    ): PeopleRepository
}
