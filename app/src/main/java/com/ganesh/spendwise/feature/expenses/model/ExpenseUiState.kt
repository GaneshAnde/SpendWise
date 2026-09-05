package com.ganesh.spendwise.feature.expenses.model

import com.ganesh.spendwise.domain.model.Expense

data class ExpenseUiState(
    val expense: Expense? = null,
    val isLoading : Boolean = true,
    val error : String? = ""
)