package com.wink.app.winkapptest.navigation.direction

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.wink.app.winkapptest.navigation.NavigationAction
import com.wink.app.winkapptest.navigation.NavigationCommand

object AppDirections {

    object List : NavigationCommand {

        override val route: String = "list"
        override val arguments = emptyList<NamedNavArgument>()
        val destination = NavigationAction.Destination(route = "list")

    }

    object Detail : NavigationCommand {

        const val ID = "id"
        override val route: String = "detail/{$ID}"
        override val arguments =
            listOf(navArgument(ID) { type = NavType.StringType })

        fun destination(id: String) =
            NavigationAction.Destination(route = "detail/${id}")

    }

}