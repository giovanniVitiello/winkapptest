package com.wink.app.winkapptest.di

import com.wink.app.data.repository.UnsplashRepositoryImpl
import com.wink.app.domain.repository.UnsplashRepository
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
    abstract fun bindUnsplashRepository(
        impl: UnsplashRepositoryImpl
    ): UnsplashRepository
}