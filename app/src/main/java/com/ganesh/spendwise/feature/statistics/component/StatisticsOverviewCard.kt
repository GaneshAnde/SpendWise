package com.ganesh.spendwise.feature.statistics.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ganesh.spendwise.feature.statistics.model.StatisticsUiState

@Composable
fun StatisticsOverviewCard(
    uiState: StatisticsUiState,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = "Overview",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatisticsCard(modifier = Modifier.weight(1f), title = "Total Spend", value = uiState.totalSpent)
            StatisticsCard(modifier = Modifier.weight(1f),title = "Transactions", value = 42.toDouble())
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatisticsCard(modifier = Modifier.weight(1f),title = "Average", value = 55.72)
            StatisticsCard(modifier = Modifier.weight(1f),title = "Highest Expense", value = 42.toDouble())
        }
    }



}