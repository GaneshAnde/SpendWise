package com.ganesh.spendwise.feature.expenses.model

import com.ganesh.spendwise.domain.model.Category

data class CategorySummary(
    val category : Category,
    val totalSpend : Double
) {
}