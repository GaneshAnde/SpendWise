package com.ganesh.spendwise.data.datasource

import com.ganesh.spendwise.data.dto.ExpenseDto

interface LocalExpenseDataSource {
    suspend fun getExpenses() : List<ExpenseDto>
}