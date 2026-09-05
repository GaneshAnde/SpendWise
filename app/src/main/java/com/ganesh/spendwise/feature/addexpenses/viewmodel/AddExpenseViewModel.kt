package com.ganesh.spendwise.feature.addexpenses.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense
import com.ganesh.spendwise.domain.repository.ExpenseRepository
import com.ganesh.spendwise.feature.addexpenses.model.AddExpenseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class AddExpenseViewModel @Inject constructor(private val repository: ExpenseRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AddExpenseUiState())
    val uiState = _uiState.asStateFlow()

    fun onMerchantChanged(value: String) {
        _uiState.update { it.copy(merchant = value) }
    }

    fun onAmountChanged(value: String) {
        _uiState.update { it.copy(amount = value) }
    }

    fun onCategoryChanged(category: Category) {
        _uiState.update { it.copy(category = category) }
    }

    fun onDateChanged(date: LocalDate) {
        _uiState.update { it.copy(date = date) }
    }

    fun onNotesChanged(notes: String) {
        _uiState.update { it.copy(notes = notes) }
    }


    fun updateExpense(id : Long){
        val state = uiState.value

        val expense = Expense(
            id = id,
            merchant = state.merchant,
            amount = state.amount.toDouble(),
            category = state.category,
            date = state.date.toString(),
            note = state.notes
        )

        viewModelScope.launch {
            val result = repository.updateExpense(expense)
            if(result > 0){
                _uiState.update {
                    it.copy(
                        isSaved = true
                    )
                }
            }
        }

    }
    fun saveExpense(

    ) {

        val state = uiState.value

        val expense = Expense(
            id = System.currentTimeMillis(),
            merchant = state.merchant,
            amount = state.amount.toDouble(),
            category = state.category,
            date = state.date.toString(),
            note = state.notes
        )

        viewModelScope.launch {

            repository.addExpense(expense)

            _uiState.update {
                it.copy(
                    isSaved = true
                )
            }

        }
    }

    fun getExpense(expenseId : Long){

        viewModelScope.launch {

            val expense: Expense = repository.getExpense(expenseId) ?: return@launch


            _uiState.update {
                it.copy(
                    merchant = expense.merchant,
                    amount = expense.amount.toString(),
                    date = expense.dateToLocalDate(),
                    notes = expense.note
                )
            }
        }
    }


}