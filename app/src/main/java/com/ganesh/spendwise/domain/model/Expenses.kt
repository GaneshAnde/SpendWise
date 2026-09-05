package com.ganesh.spendwise.domain.model

import java.time.LocalDate

data class Expense(
    val id: Long,
    val merchant: String,
    val amount: Double,
    val category: Category,
    val date: String,
    val note: String = ""
){
    fun dateToLocalDate() : LocalDate{
        return LocalDate.parse(date)
    }
}