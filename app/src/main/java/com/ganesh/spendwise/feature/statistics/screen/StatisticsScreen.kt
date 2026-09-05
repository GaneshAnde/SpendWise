package com.ganesh.spendwise.feature.statistics.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ganesh.spendwise.feature.statistics.component.CategorySummary
import com.ganesh.spendwise.feature.statistics.component.StatisticToolbar
import com.ganesh.spendwise.feature.statistics.component.StatisticsOverviewCard
import com.ganesh.spendwise.feature.statistics.viewmodel.StatisticsViewModel


@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier
) {


    val viewModel : StatisticsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

/*    Column(
        modifier = modifier.padding(16.dp),

    ){
      Text(
          text = "Total Spending",
          style = MaterialTheme.typography.bodyMedium
      )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$${uiState.totalSpent}",
            style = MaterialTheme.typography.bodyMedium
        )
    }*/

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item{
            StatisticToolbar {

            }
        }


        item {
            StatisticsOverviewCard(uiState, modifier = Modifier.padding(horizontal = 16.dp))
        }

        item {
            CategorySummary(uiState.categorySummary)
        }



    }
/*    Scaffold() {    paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }

    }*/


}
