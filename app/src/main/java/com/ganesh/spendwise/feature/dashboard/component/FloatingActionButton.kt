package com.ganesh.spendwise.feature.dashboard.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButton(
    onclick : () -> Unit
){
    FloatingActionButton(
        onClick = onclick,
        modifier = Modifier.padding(bottom = 100.dp)
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}