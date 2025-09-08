package com.wink.app.winkapptest.di

import com.wink.app.winkapptest.navigation.NavigationManager
import com.wink.app.winkapptest.navigation.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigationManager(
        getNavigationManager: NavigationManagerImpl
    ): NavigationManager

}