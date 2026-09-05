package com.ganesh.spendwise.feature.addexpenses.model

import com.ganesh.spendwise.domain.model.Category
import java.time.LocalDate

data class AddExpenseUiState(
    val merchant: String = "",
    val amount: String = "",
    val category: Category = Category.OTHER,
    val date: LocalDate = LocalDate.now(),
    val notes: String = "",
    val isSaved : Boolean = false
){
    val isSaveEnabled : Boolean
        get() = merchant.isNotBlank() &&
                amount.toDoubleOrNull() != null &&
                amount.toDouble() > 0
}