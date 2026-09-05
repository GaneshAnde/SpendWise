package com.ganesh.spendwise.domain.repository

import androidx.room.Query
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense
import com.ganesh.spendwise.feature.dashboard.model.SortOption
import com.ganesh.spendwise.feature.expenses.model.CategorySummary
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun getExpenses(query: String, category: Category?, sortOption: SortOption) : Flow<List<Expense>>

    suspend fun addExpense(expense: Expense)

    suspend fun getExpense(id : Long) : Expense?

    suspend fun deleteExpense(expense: Expense)

    suspend fun restoreExpense(expense: Expense)


    suspend fun updateExpense(expense: Expense) : Int

    suspend fun observeTotalSpent() : Flow<Double>

    suspend fun observeCategorySummary() : Flow<List<CategorySummary>>

}