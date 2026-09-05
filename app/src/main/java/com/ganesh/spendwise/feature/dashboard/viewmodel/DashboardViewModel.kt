package com.ganesh.spendwise.feature.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense
import com.ganesh.spendwise.domain.repository.ExpenseRepository
import com.ganesh.spendwise.feature.dashboard.model.DashboardFilters
import com.ganesh.spendwise.feature.dashboard.model.DashboardUiEvent
import com.ganesh.spendwise.feature.dashboard.model.DashboardUiState
import com.ganesh.spendwise.feature.dashboard.model.SortOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: ExpenseRepository
)  : ViewModel(){

    val searchQuery = MutableStateFlow("")

    val selectedCategory : MutableStateFlow<Category?> = MutableStateFlow(null)

    val selectedSortOption : MutableStateFlow<SortOption> = MutableStateFlow(SortOption.DATE_DESC)


    private val _events = MutableSharedFlow<DashboardUiEvent>()

    private val pendingDeletionIds = MutableStateFlow<Set<Long>>(emptySet())
    val events = _events.asSharedFlow()
    private val _uiState = MutableStateFlow(
        DashboardUiState(
            isLoading = true
        )
    )

    val uiState : StateFlow<DashboardUiState> =_uiState.asStateFlow()



    init {

        viewModelScope.launch {
            combine(
                searchQuery, selectedCategory, selectedSortOption, pendingDeletionIds
            ){ query, category, sortOption, deleteIds ->
                DashboardFilters(
                    query = query, category = category, sortOption = sortOption, pendingDeletionIds = deleteIds
                )

            }.flatMapLatest { filters ->
                repository.getExpenses(
                    query = filters.query,
                    sortOption = filters.sortOption,
                    category = filters.category,
                ).map { expenses ->
                    expenses.filterNot {
                        it.id in filters.pendingDeletionIds
                    }
                }

            }.collect { expenses ->
                _uiState.update {
                    it.copy(
                        expense = expenses,
                        isLoading = false
                    )
                }
            }
        }

//        viewModelScope.launch {
//            combine(
//                searchQuery, selectedCategory, selectedSortOption, pendingDeletionIds
//            ){ query, category, sortOption->
//                Triple(query, category, sortOption)
//            }.flatMapLatest { (query, category, sortOption, ) ->
//                repository.getExpenses(query, category, sortOption)
//            }.collect { expenses ->
//                _uiState.update {
//                    it.copy(
//                        expense = expenses,
//                        isLoading = false
//                    )
//                }
//            }
//        }
        //loadExpense("")
    }

/*    private fun loadExpense(query : String) {
        viewModelScope.launch {
            try {
               // val expenses = repository.getExpenses()

                repository.getExpenses(query).collect { expenses ->
                    _uiState.value = _uiState.value.copy(
                        expense = expenses,
                        isLoading = false
                    )
                }
            }catch (e : Exception){
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }

        }
    }*/

    fun onSearchQueryChanged(query : String){

        searchQuery.value = query

        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            isLoading = query.isBlank()
        )

       /* viewModelScope.launch {
            searchQuery.debounce(300.milliseconds)
                .distinctUntilChanged()
                .flatMapLatest{ query ->
                    *//*if (query.isBlank()){
                        _uiState.value = _uiState.value.copy(
                            isLoading = true
                        )
                    }*//*
                    repository.getExpenses(query)
                }.collect { expenses ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        expense = expenses
                    )
                }
        }*/


    }

    fun onCategorySelected(category: Category?){
        selectedCategory.value = category

        _uiState.value = _uiState.value.copy(
            selectedCategory = category
        )
    }

    fun onSortSelected(sortOption: SortOption) {
        selectedSortOption.value = sortOption

        _uiState.update {
            it.copy(
                sortOption = sortOption
            )
        }
    }

    fun updatePendingIds(expense: Expense){
        pendingDeletionIds.value += expense.id
        viewModelScope.launch {
            _events.emit(
                DashboardUiEvent.ShowUndoDelete(expense)
            )
        }
    }
    fun deleteExpense(expense : Expense){
        viewModelScope.launch {
            repository.deleteExpense(expense)
//            _events.emit(
//                DashboardUiEvent.ShowUndoDelete(expense)
//            )
        }

    }

    fun restoreExpense(expense: Expense){
//        viewModelScope.launch {
//            repository.addExpense(expense)
//        }
        pendingDeletionIds.value -= expense.id
    }
}