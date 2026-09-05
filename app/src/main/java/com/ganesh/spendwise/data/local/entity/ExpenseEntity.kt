package com.ganesh.spendwise.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val merchant: String,

    val amount: Double,

    val category: String,

    val date: String,

    val notes: String
)