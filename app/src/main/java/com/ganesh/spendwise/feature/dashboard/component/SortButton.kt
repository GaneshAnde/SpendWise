package com.ganesh.spendwise.feature.dashboard.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ganesh.spendwise.feature.dashboard.model.SortOption

@Composable
fun SortButton(
    sortOption: SortOption,
    onSortSelected : (SortOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box() {
     TextButton(
            onClick = {
                expanded = true
            }
        ) {


            Text(sortOption.buttonLabel,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
               imageVector =  Icons.Default.ArrowDropDown,
                contentDescription = null
            )


        }

        /*OutlinedButton(
            onClick = {
                expanded = true
            },
            modifier = Modifier.padding(end = 16.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(sortOption.buttonLabel, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector =  Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }*/

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            SortOption.entries.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(option.menuLabel)
                    },
                    onClick = {
                        expanded = false
                        onSortSelected(option)
                    }
                )
            }
        }
    }




}