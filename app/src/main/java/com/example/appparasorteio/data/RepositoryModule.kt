package com.example.appparasorteio.data


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFilmeRepository(
        filmeDao: FilmeDao,
    ): FilmeRepository {
        return FilmeRepository(
            dao = filmeDao,
            apiKey = com.example.appparasorteio.BuildConfig.TMDB_API_KEY
        )
    }
}
