package com.ganesh.spendwise.core.util


import java.text.NumberFormat
import java.util.Locale


object CurrencyFormatter {

    private val formatter = NumberFormat.getCurrencyInstance(Locale.US);

    fun format(amount: Double) :  String{
        return formatter.format(amount)
    }



}