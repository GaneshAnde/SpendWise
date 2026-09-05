package com.ganesh.spendwise.feature.statistics.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticToolbar(
    onFilterClick : () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = "Statistics",
            )
        }, actions = {
            IconButton(
                onClick = onFilterClick
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Select Date Range"
                )
            }
        }
    )
}