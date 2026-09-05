package com.ganesh.spendwise.feature.dashboard.component

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  DashboardTopBar(
    title : String,
    isBackEnabled : Boolean = false,
    onBackClick : () -> Unit,
    modifier: Modifier
){
    TopAppBar(
        title = {
            Text(text = title)
        },

        navigationIcon = {
            if(isBackEnabled) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }, modifier = modifier
    )
}