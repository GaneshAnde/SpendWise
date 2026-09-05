package com.ganesh.spendwise.feature.addexpenses.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ganesh.spendwise.feature.addexpenses.component.AddExpenseContent
import com.ganesh.spendwise.feature.addexpenses.viewmodel.AddExpenseViewModel
import com.ganesh.spendwise.feature.dashboard.component.DashboardTopBar

@Composable
fun AddExpenseScreen(
    expenseId : Long?,
    onBackClick: () -> Unit,

) {

    val viewModel : AddExpenseViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(uiState.isSaved) {
        if(uiState.isSaved){
            onBackClick()
        }
    }

    LaunchedEffect(
        Unit
    ) {
        if(expenseId != null)
            viewModel.getExpense(expenseId)
    }






    Scaffold(
        topBar = {
            DashboardTopBar(
                title = "Add Expense",
                isBackEnabled = true,
                onBackClick = onBackClick,
                modifier = Modifier.padding(1.dp)
            )
        }
    ) { padding ->

        AddExpenseContent(
            modifier = Modifier.padding(padding),
            uiState = uiState,
            onMerchantChanged = viewModel::onMerchantChanged,
            onAmountChanged = viewModel::onAmountChanged,
            onNotesChanged = viewModel::onNotesChanged,
            onCategoryChanged = viewModel::onCategoryChanged,
            onDateChanged = viewModel::onDateChanged,
            onSaveClick = {
                if (expenseId != null){
                    viewModel.updateExpense(expenseId)
                }else viewModel.saveExpense()
            }
        )
    }
}

//@Composable
//@Preview
//fun AddExpenseScreenPreview(){
//    AddExpenseScreen(
//        AddExpenseViewModel(),
//        onBackClick = {},
//        onSaveClick = {}
//    )
//}