package com.ganesh.spendwise.feature.dashboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ganesh.spendwise.feature.dashboard.viewmodel.DashboardViewModel


@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    /*DashboardScreen(
        uiState = uiState,
        onBackClick = {

        },
        onFabClick = {

        }
    )*/
}