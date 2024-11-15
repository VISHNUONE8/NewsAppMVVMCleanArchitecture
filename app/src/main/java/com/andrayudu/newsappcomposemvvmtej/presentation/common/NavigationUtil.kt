package com.andrayudu.newsappcomposemvvmtej.presentation.common

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.screens.Route

object NavigationUtil {

    fun navigateSingleTopTo(
        route: String,
        navController: NavHostController
    ) {
        navController.navigate(route) {
            //pops all the destinations except the start Destination i.e NewsScreen when navigating..
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }


}