package com.ganesh.spendwise.feature.statistics.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ganesh.spendwise.domain.repository.ExpenseRepository
import com.ganesh.spendwise.feature.statistics.model.StatisticsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel(){

    private val _uiState : MutableStateFlow<StatisticsUiState> = MutableStateFlow(StatisticsUiState())

    val uiState = _uiState.asStateFlow()




    fun getCategorySummary(){
        viewModelScope.launch {
            repository.observeCategorySummary().collect {  categorySummaries ->
                _uiState.update {
                    it.copy(
                        categorySummary = categorySummaries
                    )
                }
            }
        }
    }


    fun getTotalSpent(){

        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            repository.observeTotalSpent().collect{ total ->
                _uiState.update {
                    it.copy(
                        totalSpent = total,
                        isLoading = false

                    )
                }
            }
        }
    }


    init {
        Log.d("Statistics", "ViewModel Init")
        getTotalSpent()
        getCategorySummary()
    }
}