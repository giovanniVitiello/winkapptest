package com.wink.app.winkapptest.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wink.app.winkapptest.ext.catchIgnoreError
import com.wink.app.winkapptest.navigation.direction.AppDirections
import com.wink.app.winkapptest.ui.screen.detail.DetailScreen
import kotlinx.coroutines.flow.collect
import com.wink.app.winkapptest.ui.screen.list.ListScreen
import kotlinx.coroutines.flow.onEach

@Composable
fun AppNavigation(
    navigationManager: NavigationManager
) {
    val navController = rememberNavController()
    Scaffold { innerPadding ->

        LaunchedEffect(key1 = "wink_app_test") {
            navigationManager.actions
                .onEach { action ->
                    when (action) {
                        NavigationAction.Back -> {
                            catchIgnoreError {
                                navController.popBackStack()
                            }
                        }

                        is NavigationAction.Destination ->
                            if (action.route.isNotEmpty()) {
                                catchIgnoreError {
                                    navController.navigate(action.route, action.options)
                                }
                            }
                    }
                }
                .collect()
        }

        NavHost(
            navController = navController,
            startDestination = AppDirections.List.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            appNavigation()
        }
    }
}

private fun NavGraphBuilder.appNavigation() {
    composable(AppDirections.List.route) {
        ListScreen()
    }
    composable(AppDirections.Detail.route) {
        DetailScreen()
    }
}