package com.ganesh.spendwise.feature.dashboard.model

import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense

data class DashboardUiState(
    val isLoading : Boolean = false,
    val searchQuery : String = "",
    val selectedCategory: Category? = null,
    val sortOption: SortOption = SortOption.DATE_DESC,
    val expense: List<Expense> = emptyList(),
    val error : String? = null
){
    val showEmptyExpense : Boolean
        get() = expense.isEmpty() && searchQuery.isBlank() && selectedCategory?.name?.isBlank() ?: false

    val showNoSearchExpenseResult : Boolean
        get() = expense.isEmpty() && searchQuery.isNotBlank()

    val showNoCategoryExpenseResult : Boolean
        get() = expense.isEmpty() && selectedCategory?.name?.isNotBlank() ?: false
}