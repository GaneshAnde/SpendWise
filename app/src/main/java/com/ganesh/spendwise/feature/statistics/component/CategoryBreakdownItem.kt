package com.ganesh.spendwise.feature.statistics.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ganesh.spendwise.feature.expenses.model.CategorySummary

@Composable
fun CategoryBreakdownItem(
    summary: CategorySummary
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = summary.category.emoji,
                fontSize = 22.sp
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Text(
                text = summary.category.displayName,
                style = MaterialTheme.typography.bodyLarge
            )

        }

        Text(
            text = "$%.2f".format(summary.totalSpend),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

    }

}