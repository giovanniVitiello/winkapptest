package com.wink.app.winkapptest.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface NavigationManager {
    suspend fun navigate(directions: NavigationAction)
    suspend fun back()

    val actions: SharedFlow<NavigationAction>
}

@Singleton
class NavigationManagerImpl @Inject constructor() : NavigationManager {

    private val actionsMutableSharedFlow = MutableSharedFlow<NavigationAction>(replay = 0)
    override val actions = actionsMutableSharedFlow.asSharedFlow()

    override suspend fun navigate(
        directions: NavigationAction
    ) {
        actionsMutableSharedFlow.emit(directions)
    }

    override suspend fun back() {
        actionsMutableSharedFlow.emit(NavigationAction.Back)
    }

}