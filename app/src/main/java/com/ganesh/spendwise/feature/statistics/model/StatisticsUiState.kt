package com.ganesh.spendwise.feature.statistics.model

import com.ganesh.spendwise.feature.expenses.model.CategorySummary

data class StatisticsUiState(
    val totalSpent : Double = 0.00,
    val categorySummary : List<CategorySummary> = emptyList(),
    val isLoading : Boolean = false
) {
}