package com.ganesh.spendwise.data.datasource

import com.ganesh.spendwise.data.dto.ExpenseDto
import kotlinx.serialization.json.Json

class JsonExpenseDataSource(
    private val fileDataSource: FileDataSource,
    private val json : Json
)  : LocalExpenseDataSource{


    override suspend fun getExpenses(): List<ExpenseDto> {


        val jsonString : String =  fileDataSource.read("expenses.json")
        return json.decodeFromString(jsonString)
    }
}