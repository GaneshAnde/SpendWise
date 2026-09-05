package com.ganesh.spendwise.feature.dashboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.ganesh.spendwise.domain.model.Expense
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ganesh.spendwise.feature.dashboard.model.SortOption

@Composable
fun RecentExpenseCard(expenses: List<Expense>,
                      selectedSortOption : SortOption,
                      onSortSelected : (SortOption) -> Unit,
                      onExpenseClick: (Long) -> Unit) {
    Column(
        modifier = Modifier.padding(top = 8.dp)
    ) {

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
            sortOption = selectedSortOption,
            onSortSelected = { sortOption ->
                onSortSelected(sortOption)
            }
        )
        }
        /*Text(
            text = "Recent Expenses",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp )

        )*/
        /*LazyColumn() {
            items(expenses){ expense ->
                RecentExpenseItem(expense)
            }
        }*/

        expenses.forEach {
            RecentExpenseItem(it, onExpenseClick = {
                onExpenseClick(it.id)
            })
        }
    }
}