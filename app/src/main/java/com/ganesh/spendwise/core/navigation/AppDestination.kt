package com.ganesh.spendwise.core.navigation

sealed class AppDestination(val route: String) {

    data object Dashboard : AppDestination("dashboard")

    data object AddExpense : AppDestination("add_expense/{expenseId}"){
        fun createRoute(id : Long?) : String{
            return "add_expense/$id"
        }
    }

    data object ExpenseDetails : AppDestination("expense_details/{expenseId}") {

        fun createRoute(id: Long): String {
            return "expense_details/$id"
        }
    }


    data object Statistics : AppDestination("statistics")
}


/*

object AppDestination{

    const val Dashboard = "dashboard"
    const val AddExpense  ="add_expense"
    const val ExpenseDetails = "expense_details/{expenseId}"

    fun createRoute(expenseId : Long): String {
        return "expense_details/$expenseId"
    }


}

*/
