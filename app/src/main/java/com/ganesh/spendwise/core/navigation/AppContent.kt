package com.ganesh.spendwise.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppContent(
    navController: NavHostController
) {

    val backStackEntry by navController.currentBackStackEntryAsState()


    val currentRoute =
        backStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        AppDestination.Dashboard.route,
        AppDestination.Statistics.route
    )

    Scaffold(

        bottomBar = {
            if (showBottomBar) {


                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onItemClick = { destination ->

                        navController.navigate(destination.route) {

                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }

    ) { padding ->

        SpendWiseNavHost(
            navController = navController,
            modifier = Modifier.padding(padding)
        )

    }
}

