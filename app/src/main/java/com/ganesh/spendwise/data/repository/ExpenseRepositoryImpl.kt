package com.ganesh.spendwise.data.repository

import androidx.room.Query
import com.ganesh.spendwise.data.datasource.LocalExpenseDataSource
import com.ganesh.spendwise.data.local.datasource.ExpenseLocalDataSource
import com.ganesh.spendwise.data.local.mapper.toDomain
import com.ganesh.spendwise.data.local.mapper.toEntity
import com.ganesh.spendwise.data.mapper.toDomain
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense
import com.ganesh.spendwise.domain.repository.ExpenseRepository
import com.ganesh.spendwise.feature.dashboard.model.SortOption
import com.ganesh.spendwise.feature.expenses.model.CategorySummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val dataSource: ExpenseLocalDataSource
) : ExpenseRepository {

    private val expenses = mutableListOf<Expense>()

    private var initialized = false

    override suspend fun getExpenses(query: String, category: Category?, sortOption: SortOption): Flow<List<Expense>> {


        return dataSource.getExpenses(query, category, sortOption).map { list ->
            list.map {
                it.toDomain()
            }
        }

    }

    override suspend fun addExpense(expense: Expense) {
        dataSource.insertExpense(expense.toEntity())
    }

    override suspend fun getExpense(id: Long): Expense? {
        return dataSource.getExpense(id)?.toDomain()
    }

    override suspend fun deleteExpense(expense: Expense) {
         dataSource.deleteExpense(expense.toEntity())
    }

    override suspend fun restoreExpense(expense: Expense){
        dataSource.insertExpense(expense.toEntity())
    }


    override suspend fun updateExpense(expense: Expense): Int {
        return dataSource.updateExpense(expense.toEntity())
    }

    override suspend fun observeTotalSpent(): Flow<Double> {
        return dataSource.observeTotalSpent()
    }


    override suspend fun observeCategorySummary(): Flow<List<CategorySummary>> {
        return dataSource.observeCategorySummary()
    }




}