package com.pluck.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PLuckChipRow(
    chips: List<String>,
    selectedChip: String,
    onSelect: (String) -> Unit
) {

    Surface(
        shape = RoundedCornerShape(32.dp),
        color = PluckPalette.Surface,
        shadowElevation = 12.dp,
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            for (chip in chips) {
                val selected = chip == selectedChip
                item{
                    FilterChip(
                        selected = selected,
                        onClick = { onSelect(chip) },
                        label = {
                            Text(
                                text = chip,
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = if (selected) PluckPalette.Primary else PluckPalette.Surface,
                            labelColor = if (selected) PluckPalette.Surface else PluckPalette.Primary,
                            selectedContainerColor = PluckPalette.Primary,
                            selectedLabelColor = PluckPalette.Surface
                        )
                    )
                }
            }
        }
    }
}
