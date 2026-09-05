package com.ganesh.spendwise.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ganesh.spendwise.data.local.dao.ExpenseDao

import com.ganesh.spendwise.data.local.entity.ExpenseEntity


@Database(
    entities = [ExpenseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SpendWiseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
}