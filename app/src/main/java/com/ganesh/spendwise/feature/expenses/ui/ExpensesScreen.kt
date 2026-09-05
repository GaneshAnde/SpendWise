package com.ganesh.spendwise.feature.expenses.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ganesh.spendwise.core.util.CurrencyFormatter
import com.ganesh.spendwise.core.util.DateFormatter
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense
import com.ganesh.spendwise.feature.dashboard.component.DashboardTopBar
import com.ganesh.spendwise.feature.dashboard.component.RecentExpenseItem
import com.ganesh.spendwise.feature.expenses.component.ExpenseInfoRow
import com.ganesh.spendwise.feature.expenses.viewmodel.ExpensesViewModel


@Composable
fun ExpenseScreen(
    id: Long,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {


 //  viewModel.getExpenseData(id)

    Log.d("Expense", "ExpenseScreen composed")
    val viewModel : ExpensesViewModel = hiltViewModel()
/*    LaunchedEffect(id) {
        Log.d("Expense", "LaunchedEffect")

        viewModel.getExpenseData(id)
    }*/

    //viewModel.getExpenseData(id)


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Log.d("Expense", "Expense = ${uiState.expense}")

    val expense : Expense? = uiState.expense


        LaunchedEffect(id) {
        Log.d("Expense", "LaunchedEffect")

        viewModel.getExpenseData(id)
    }


    //viewModel.getExpenseData(id)

    Scaffold(
        topBar = {
            DashboardTopBar(
                title = "Expense",
                isBackEnabled = true,
                onBackClick = onBackClick,
                modifier = Modifier.padding(0.dp)
            )
        }
    ) { padding ->

        if(uiState.isLoading){
            CircularProgressIndicator()
        }else if (uiState.expense == null){
            onBackClick()
        }else

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            // Header
            Text(
                text = expense!!.merchant,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = expense.note,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    ExpenseInfoRow(
                        title = "Amount",
                        value = CurrencyFormatter.format(expense.amount)
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    ExpenseInfoRow(
                        title = "Category",
                        value = expense.category.name.replaceFirstChar {
                            it.uppercase()
                        }
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    ExpenseInfoRow(
                        title = "Date",
                        value = DateFormatter.format(expense.date)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Notes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = expense.note,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onEditClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Edit Expense",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


/*@Composable
fun ExpensesScreen(expense: Expense, onBackClick : () -> Unit){
    Scaffold(
        topBar = {
            DashboardTopBar(
            title = "Expenses",
            isBackEnabled = true,
            onBackClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = expense.merchant,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = expense.note,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Amount",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = CurrencyFormatter.format(expense.amount),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Category",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Cte",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = CurrencyFormatter.format(expense.amount),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(8.dp))
            }



        }
    }
}*/

//@Preview
//@Composable
//fun ExpensesScreenPreview(){
//     val expense = Expense( 2,
//        "Amazon",
//        39.99,
//        Category.from("SHOPPING"),
//        "2026-07-18",
//        "USB Cable")
//
//    ExpenseScreen(expense, onBackClick = {
//
//    })
//
//
//}


