package com.pluck.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * A full screen dialogue that asks for date input
 * @param onDateSelected Executes when the user selects a date
 * @param onDismiss Executes when the user tries to dismiss the dialogue
 * @param defaultDate The default value to be selected before the user selects a date, and also
 *      the default date that will be selected if the user selects no date
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PLuckDatePicker(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
    defaultDate: LocalDate
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = defaultDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )

    DatePickerDialog(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(
                    Instant.ofEpochMilli(datePickerState.selectedDateMillis ?: Instant.now().toEpochMilli()).atZone(
                    ZoneId.of("UTC")).toLocalDate())
                onDismiss()
            }) {
                Text("Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
