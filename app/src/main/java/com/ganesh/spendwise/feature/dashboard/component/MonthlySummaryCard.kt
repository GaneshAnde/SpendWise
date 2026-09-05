package com.ganesh.spendwise.feature.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MonthlySummaryCard(expense : Double){
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp )
            .fillMaxWidth()
            .background(Color.White),
        elevation = CardDefaults.elevatedCardElevation(6.dp),

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "This month",
                fontSize = 16.sp,
                color = Color.Black
            )

            Text(
                text = "$ $expense",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "+12% from last month",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )


        }
    }

}

@Preview
@Composable
fun MonthlySummaryCardPreview(){
    MonthlySummaryCard(123.45)
}