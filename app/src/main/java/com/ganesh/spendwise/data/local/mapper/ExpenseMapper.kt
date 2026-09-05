package com.ganesh.spendwise.data.local.mapper

import com.ganesh.spendwise.data.local.entity.ExpenseEntity
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense

fun ExpenseEntity.toDomain() =
    Expense(
        id = id,
        merchant = merchant,
        amount = amount,
        category =  Category.from(category),
        date = date,
        note = notes
    )

fun Expense.toEntity() =
    ExpenseEntity(
        id = id,
        merchant = merchant,
        amount = amount,
        category = category.name,
        date = date,
        notes = note
    )