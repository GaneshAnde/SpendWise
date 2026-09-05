package com.ganesh.spendwise.feature.expenses.viewmodel

import android.util.Log
import androidx.annotation.IntDef
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.ganesh.spendwise.domain.model.Expense
import com.ganesh.spendwise.domain.repository.ExpenseRepository
import com.ganesh.spendwise.feature.expenses.model.ExpenseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel(){


    private val  _uiState  = MutableStateFlow(ExpenseUiState())

    val uiState = _uiState.asStateFlow()

    fun getExpenseData(id : Long){

        Log.d("flicker","get Expense data checking")
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            val expense = expenseRepository.getExpense(id);
            Log.d("flicker","inside view model scope checking")
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                expense = expense
            )
        }

    }


}