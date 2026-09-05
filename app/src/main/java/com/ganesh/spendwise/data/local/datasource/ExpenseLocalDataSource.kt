package com.ganesh.spendwise.data.local.datasource

import androidx.room.Query
import com.ganesh.spendwise.data.local.dao.ExpenseDao
import com.ganesh.spendwise.data.local.entity.ExpenseEntity
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.feature.dashboard.model.SortOption
import com.ganesh.spendwise.feature.expenses.model.CategorySummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseLocalDataSource @Inject constructor(
    private val expenseDao: ExpenseDao
) {

    fun getExpenses(query: String, category: Category?, sortOption: SortOption) : Flow<List<ExpenseEntity>> {
        return when(sortOption){
            SortOption.DATE_ASC ->
                expenseDao.getExpensesByDateAsc(query, category?.name)

            SortOption.AMOUNT_DESC ->
                expenseDao.getExpensesByAmountDesc(query, category?.name)

            SortOption.AMOUNT_ASC ->
                expenseDao.getExpensesByAmountAsc(query, category?.name)

            else ->
                expenseDao.getExpensesByDateDesc(query, category?.name)
        }

    }



    suspend fun getExpense(id: Long) =
        expenseDao.getExpense(id)

    suspend fun insertExpense(expense: ExpenseEntity) =
        expenseDao.insertExpense(expense)

    suspend fun deleteExpense(expense: ExpenseEntity) =
        expenseDao.deleteExpense(expense)

    suspend fun updateExpense(expense: ExpenseEntity) : Int{
        return expenseDao.updateExpense(expense)
    }

    fun observeTotalSpent() : Flow<Double>{
        return expenseDao.getTotalSpent()
    }

    fun observeCategorySummary() : Flow<List<CategorySummary>>{
        return expenseDao.getCategorySummary()
    }
}