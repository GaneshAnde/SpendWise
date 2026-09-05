package com.ganesh.spendwise.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object DateFormatter {


    private val inputFormatter =
        DateTimeFormatter.ISO_LOCAL_DATE


    private val outputFormatter =
        DateTimeFormatter.ofPattern("MMM dd")


    fun format(date: String): String {
        val localDate = LocalDate.parse(date, inputFormatter)
        return localDate.format(outputFormatter)
    }
}