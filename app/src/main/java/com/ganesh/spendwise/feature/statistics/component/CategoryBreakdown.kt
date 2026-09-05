package com.ganesh.spendwise.feature.statistics.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ganesh.spendwise.feature.expenses.model.CategorySummary
/*
@Composable
fun CategoryBreakdown(
    categories : List<CategorySummary>
){
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            categories.forEach { category ->
                CategoryBreakdownItem(category)
            }
        }
    }
}*/

@Composable
fun CategoryBreakdown(
    categories: List<CategorySummary>
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
            .padding(top =8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            categories.forEach { category ->

                CategoryBreakdownItem(
                    summary = category
                )

            }

        }

    }

}

