package com.wink.app.winkapptest.navigation

import androidx.navigation.NamedNavArgument


interface NavigationCommand {

    val arguments: List<NamedNavArgument>
    val route: String

}