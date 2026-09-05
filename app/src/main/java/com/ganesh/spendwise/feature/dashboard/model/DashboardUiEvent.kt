package com.ganesh.spendwise.feature.dashboard.model

import com.ganesh.spendwise.domain.model.Expense

sealed class DashboardUiEvent {
    data class ShowUndoDelete(
        val expense: Expense
    ) : DashboardUiEvent()
}