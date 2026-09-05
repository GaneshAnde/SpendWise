package com.ganesh.spendwise.feature.dashboard.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingHeader(name : String){
    Column(
        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
    ) {
        Text(
            text = "Good morning",
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Black,
            fontSize = 16.sp
        )

        Text(
            text = "$name \uD83D\uDC4B",
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Black,
            fontSize = 16.sp
        )

    }
}