package com.ganesh.spendwise.feature.dashboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ganesh.spendwise.domain.model.Category
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import com.ganesh.spendwise.feature.dashboard.model.SortOption

@Composable
fun CategoryFilterRow(
    selectedCategory: Category?,
    selectedSortOption: SortOption,
    onChipSelected : (Category?) -> Unit,
    onSortSelected : (SortOption) -> Unit
){
    val categoryList = listOf<Category?>(null) + Category.entries

    Row() {
        LazyRow(

            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp).weight(1f)
        ) {
            items(categoryList){ category ->
                FilterChip(
                    selected = category?.name == selectedCategory?.name,
                    onClick = {
                        onChipSelected(category)
                    },
                    label = {
                        Text(
                            text = category?.name ?: "All"
                        )
                    }
                )
            }
        }

        /*Spacer(modifier = Modifier.padding(8.dp))

        SortButton(
            sortOption = selectedSortOption,
            onSortSelected = { sortOption ->
                onSortSelected(sortOption)
            }
        )*/
    }







}