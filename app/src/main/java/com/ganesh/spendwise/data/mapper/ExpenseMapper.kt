package com.ganesh.spendwise.data.mapper

import com.ganesh.spendwise.data.dto.ExpenseDto
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense

fun  ExpenseDto.toDomain() : Expense{
    return Expense(
        id = id,
        merchant = merchant,
        amount = amount,
        category = Category.from(category),
        date = date,
        note = note
    )
}