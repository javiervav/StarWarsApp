package com.example.presentation.di

import com.example.domain.repository.CharactersRepository
import com.example.domain.usecase.GetCharactersUseCase
import com.example.presentation.characterslist.mapper.CharacterMapper
import com.example.presentation.characterslist.mapper.CharacterMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object PresentationProvidesModule {

    @Provides
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepository)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class PresentationBindsModule {

    @Binds
    abstract fun bindCharactersMapper(characterMapperImpl: CharacterMapperImpl): CharacterMapper
}
