package com.ganesh.spendwise.feature.statistics.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ganesh.spendwise.feature.expenses.model.CategorySummary



@Composable
fun CategorySummary(summary: List<CategorySummary>) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Category Breakdown",
            style = MaterialTheme.typography.titleMedium
        )

        CategoryBreakdown(summary)
    }
}

