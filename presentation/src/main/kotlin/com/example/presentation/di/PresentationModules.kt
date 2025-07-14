package com.example.presentation.di

import com.example.domain.repository.PeopleRepository
import com.example.domain.usecase.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCharactersUseCase(peopleRepository: PeopleRepository): GetCharactersUseCase {
        return GetCharactersUseCase(peopleRepository)
    }
}
