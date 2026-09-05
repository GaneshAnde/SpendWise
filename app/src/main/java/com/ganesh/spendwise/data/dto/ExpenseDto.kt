package com.ganesh.spendwise.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExpenseDto(
    @SerialName("id")
    val id: Long,

    @SerialName("merchant")
    val merchant: String,

    @SerialName("amount")
    val amount: Double,

    @SerialName("category")
    val category: String,

    @SerialName("date")
    val date: String,

    @SerialName("note")
    val note: String

)