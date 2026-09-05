package com.ganesh.spendwise.feature.dashboard.component

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ganesh.spendwise.core.util.CurrencyFormatter
import com.ganesh.spendwise.core.util.DateFormatter
import com.ganesh.spendwise.domain.model.Category
import com.ganesh.spendwise.domain.model.Expense
import java.nio.file.WatchEvent

@Composable
fun RecentExpenseItem(expense: Expense, onExpenseClick : () -> Unit) {
    //Column() {
    Card(
//        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
//            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        onClick = onExpenseClick
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = expense.merchant,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = CurrencyFormatter.format(expense.amount),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = expense.note,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = DateFormatter.format(expense.date),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }

    }

}

//@Preview
//@Composable
//fun RecentExpenseItemPreview(){
//    RecentExpenseItem(Expense( 2,
//        "Amazon",
//        39.99,
//        Category.from("SHOPPING"),
//        "2026-07-18",
//        "USB Cable"))
//}

//@Composable
//fun RecentExpenseItem(
//    expense: Expense,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 8.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//                Text(
//                    text = expense.merchant,
//                    style = MaterialTheme.typography.titleMedium
//                )
//
//                Text(
//                    text = "$${"%.2f".format(expense.amount)}",
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.SemiBold
//                )
//            }
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Text(
//                text = expense.note,
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//
//            Spacer(modifier = Modifier.height(2.dp))
//
//            Text(
//                text = expense.date,
//                style = MaterialTheme.typography.bodySmall,
//                color = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        }
//    }
//}

