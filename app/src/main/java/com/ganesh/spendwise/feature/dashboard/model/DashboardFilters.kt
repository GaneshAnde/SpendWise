package com.ganesh.spendwise.feature.dashboard.model

import com.ganesh.spendwise.domain.model.Category

data class DashboardFilters(
    val query: String,
    val category: Category?,
    val sortOption: SortOption,
    val pendingDeletionIds: Set<Long>
)