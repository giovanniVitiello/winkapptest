package com.wink.app.winkapptest.navigation

import androidx.navigation.NavOptionsBuilder

sealed class NavigationAction {

    data object Back : NavigationAction()

    data class Destination(
        val route: String,
        val ignoreThrottle: Boolean = false,
        val periodMillis: Long = 500L,
        val options: NavOptionsBuilder.() -> Unit = {}
    ) : NavigationAction()

}