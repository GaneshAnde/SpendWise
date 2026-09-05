package com.ganesh.spendwise.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ganesh.spendwise.data.local.entity.ExpenseEntity
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.feature.expenses.model.CategorySummary
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("""
    SELECT *
    FROM expenses
    WHERE (:query = ''
       OR merchant LIKE '%' || :query || '%'
       OR notes LIKE '%' || :query || '%' )
       AND  
        (:category IS NULL or category = :category)
    ORDER BY date DESC
    """)
     fun getExpensesByDateDesc(query: String, category: String?): Flow<List<ExpenseEntity>>


    @Query("""
    SELECT *
    FROM expenses
    WHERE (:query = ''
       OR merchant LIKE '%' || :query || '%'
       OR notes LIKE '%' || :query || '%' )
       AND  
        (:category IS NULL or category = :category)
    ORDER BY date ASC
    """)
    fun getExpensesByDateAsc(query: String, category: String?): Flow<List<ExpenseEntity>>

    @Query("""
    SELECT *
    FROM expenses
    WHERE (:query = ''
       OR merchant LIKE '%' || :query || '%'
       OR notes LIKE '%' || :query || '%' )
       AND  
        (:category IS NULL or category = :category)
    ORDER BY amount DESC
    """)
     fun getExpensesByAmountDesc(query: String, category: String?): Flow<List<ExpenseEntity>>

    @Query("""
    SELECT *
    FROM expenses
    WHERE (:query = ''
       OR merchant LIKE '%' || :query || '%'
       OR notes LIKE '%' || :query || '%' )
       AND  
        (:category IS NULL or category = :category)
    ORDER BY amount ASC
    """)
     fun getExpensesByAmountAsc(query: String, category: String?): Flow<List<ExpenseEntity>>



    @Query("SELECT * FROM expenses WHERE id = :id")
    suspend fun getExpense(id: Long): ExpenseEntity?

    @Insert
    suspend fun insertExpense(expense: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)

    @Update
    suspend fun updateExpense(expense : ExpenseEntity) : Int

    @Query("""
        SELECT COALESCE(SUM(amount), 0)
        FROM expenses
    """)
    fun getTotalSpent() : Flow<Double>

    @Query("""
        SELECT category, SUM(amount) as totalSpend
        FROM expenses 
        GROUP BY category
        ORDER BY SUM(amount) DESC
    """)
    fun getCategorySummary() : Flow<List<CategorySummary>>
}