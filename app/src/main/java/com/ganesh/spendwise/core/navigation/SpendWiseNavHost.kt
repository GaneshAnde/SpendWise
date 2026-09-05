package com.ganesh.spendwise.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ganesh.spendwise.feature.addexpenses.ui.AddExpenseScreen
import com.ganesh.spendwise.feature.dashboard.ui.DashboardScreen
import com.ganesh.spendwise.feature.expenses.ui.ExpenseScreen
import com.ganesh.spendwise.feature.statistics.screen.StatisticsScreen

@Composable
fun SpendWiseNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

  //  val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Dashboard.route
    ) {

        composable(AppDestination.Dashboard.route) {

            DashboardScreen(
                onFabClick = {
                    navController.navigate(AppDestination.AddExpense.createRoute(null))
                },
                onExpenseClick = { id ->
                    navController.navigate(
                        AppDestination.ExpenseDetails.createRoute(id) // exp/id
                    )
                }
            )
        }

        composable(
            AppDestination.AddExpense.route
        ) { backStackEntry ->

            val id : Long? = backStackEntry.arguments?.getString("expenseId")?.toLongOrNull()




            AddExpenseScreen(
                expenseId = id,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            AppDestination.ExpenseDetails.route
        ) { backStackEntry ->

            Log.d("ExpenseDetails", "Composable entered")

            val id = backStackEntry.arguments
                ?.getString("expenseId")
                ?.toLongOrNull()

            if (id != null) {
                ExpenseScreen(
                    id = id,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate(AppDestination.AddExpense.createRoute(id))
                    }
                )
            }
        }

        composable(route = AppDestination.Statistics.route){
            StatisticsScreen()
        }
    }
}