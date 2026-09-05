package com.ganesh.spendwise.feature.dashboard.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ganesh.spendwise.feature.dashboard.component.CategoryFilterRow
import com.ganesh.spendwise.feature.dashboard.component.DashboardTopBar
import com.ganesh.spendwise.feature.dashboard.component.EmptyExpenseState
import com.ganesh.spendwise.feature.dashboard.component.ExpenseSearchBar
import com.ganesh.spendwise.feature.dashboard.component.FloatingActionButton
import com.ganesh.spendwise.feature.dashboard.component.GreetingHeader
import com.ganesh.spendwise.feature.dashboard.component.MonthlySummaryCard
import com.ganesh.spendwise.feature.dashboard.component.NoCategoryResultsState
import com.ganesh.spendwise.feature.dashboard.component.NoSearchResultsState
import com.ganesh.spendwise.feature.dashboard.component.RecentExpenseCard
import com.ganesh.spendwise.feature.dashboard.component.RecentExpenseItem
import com.ganesh.spendwise.feature.dashboard.component.SortButton
import com.ganesh.spendwise.feature.dashboard.model.DashboardUiEvent
import com.ganesh.spendwise.feature.dashboard.model.DashboardUiState
import com.ganesh.spendwise.feature.dashboard.viewmodel.DashboardViewModel
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onFabClick : () -> Unit,
    onExpenseClick : (Long) -> Unit
) {


    val viewModel : DashboardViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {

        viewModel.events.collect { event,  ->

            when (event) {

                is DashboardUiEvent.ShowUndoDelete -> {
                    val result = snackbarHostState.showSnackbar(
                        message = "Expense deleted",
                        actionLabel = "UNDO",
                        duration = SnackbarDuration.Short
                    )

                    when(result){
                         SnackbarResult.ActionPerformed ->{
                             viewModel.restoreExpense(event.expense)
                         }

                        SnackbarResult.Dismissed ->{
                            viewModel.deleteExpense(event.expense)
                        }
                    }

//                    if (result == SnackbarResult.ActionPerformed) {
//                        viewModel.restoreExpense(event.expense)
//                    }


                }
            }
        }
    }

    Scaffold(
        topBar = {
            DashboardTopBar(
                title = "Dashboard",
                isBackEnabled = false,
                onBackClick = {},
                Modifier.fillMaxWidth()
            )
        },

        floatingActionButton = {
            FloatingActionButton(onclick = onFabClick)
        },

        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },

    ) { paddingValues ->

        when{
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.showEmptyExpense -> {
                EmptyExpenseState(onAddExpenseClick = onFabClick,
                    modifier = Modifier.padding(paddingValues))
            }
            uiState.showNoSearchExpenseResult ->{
                NoSearchResultsState(
                    uiState.searchQuery, onClearSearch = {
                        viewModel.onSearchQueryChanged("") }
                )
            }

            uiState.showNoCategoryExpenseResult ->{
                NoCategoryResultsState(
                    uiState.selectedCategory,
                    onClearFilter = {
                        viewModel.onCategorySelected(null)
                    }
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.padding(paddingValues )
                ) {
                    item {
                        ExpenseSearchBar(
                            uiState.searchQuery,
                            viewModel::onSearchQueryChanged
                        )
                    }

                    item {
                        CategoryFilterRow(
                            uiState.selectedCategory,
                            selectedSortOption = uiState.sortOption,
                            onChipSelected = { category ->
                                viewModel.onCategorySelected(category)
                            },
                            onSortSelected = { sortOption ->
                                viewModel.onSortSelected(sortOption)
                            }
                        )
                    }

                    item{
                        GreetingHeader("Ganesh")
                    }

                    item{
                        MonthlySummaryCard(124.87)
                    }


                   /* item {
                        RecentExpenseCard(uiState.expense, onExpenseClick = onExpenseClick,
                            onSortSelected = { sortOption ->
                                viewModel.onSortSelected(sortOption)
                            },
                            selectedSortOption = uiState.sortOption
                            )
                    }*/

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "Recent Transactions",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 16.dp, top = 8.dp )

                            )


                            SortButton(
                                sortOption = uiState.sortOption,
                                onSortSelected = { sortOption ->
                                   viewModel.onSortSelected(sortOption)
                                }
                            )
                        }
                    }


                    items(
                        items = uiState.expense,
                        key = {it.id}
                        ){ expense ->


//                        val swipeState = rememberSwipeToDismissBoxState(
//                            confirmValueChange = { totalDistance ->
//                                if (totalDistance == SwipeToDismissBoxValue.EndToStart) {
//                                    viewModel.updatePendingIds(expense)
//                                }
//                                true
//                            }
//                        )

                        val swipeState = rememberSwipeToDismissBoxState()

                        LaunchedEffect(swipeState.currentValue) {
                            if(swipeState.currentValue == SwipeToDismissBoxValue.EndToStart){
                                viewModel.updatePendingIds(expense)
                                swipeState.snapTo(SwipeToDismissBoxValue.Settled)
                            }
                        }
                        val alpha by animateFloatAsState(
                            targetValue = if (swipeState.progress > 0.25) 1f else 0.5f,
                            label = ""

                        )

                        SwipeToDismissBox(
                            state = swipeState,
                            enableDismissFromEndToStart = true,
                            enableDismissFromStartToEnd = false,
                            backgroundContent = {
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(MaterialTheme.colorScheme.error)
                                        .padding(end = 16.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Row(
                                        modifier = Modifier.alpha(alpha),
                                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Delete",
                                            color = Color.White,
                                            style = MaterialTheme.typography.titleMedium,

                                        )
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            tint = Color.White,
                                            contentDescription = null

                                        )


                                    }

                                }
                            },
                           modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)

                        ) {
                            RecentExpenseItem(expense, onExpenseClick = {
                                onExpenseClick(expense.id)
                            })
                        }


                    }

                    item {
                        Spacer(modifier = Modifier.padding(32.dp))
                    }
                }
            }
        }



       /* Column(
            modifier = Modifier.padding(paddingValues )
        ) {
            GreetingHeader("Ganesh")
            MonthlySummaryCard(124.87)
            RecentExpenseCard(uiState.expense)
        }
*/




    }
}