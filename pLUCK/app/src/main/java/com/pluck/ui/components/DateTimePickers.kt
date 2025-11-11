package com.pluck.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
        DatePicker(
            state = datePickerState,
            title = { },
            headline = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Select Event Date",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Primary,
                            fontSize = 24.sp
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.8f)
        )
    }
}

/**
 * A full screen dialogue that asks for a date range
 * @param onDateSelected Executes when the user selects a date range
 * @param onDismiss Executes when the user tries to dismiss the dialogue
 * @param defaultStartDate The default value to be selected before the user selects a start date, and also
 *      the default start date that will be selected if the user selects no start date
 * @param defaultEndDate The default value to be selected before the user selects an end date, and also
 *      the default end date that will be selected if the user selects no end date
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PLuckDateRangePicker(
    onDateSelected: (LocalDate, LocalDate) -> Unit,
    onDismiss: () -> Unit,
    defaultStartDate: LocalDate,
    defaultEndDate: LocalDate,
) {
    val defaultStartMillis: Long = defaultStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    val defaultEndMillis: Long = defaultEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = defaultStartMillis,
        initialSelectedEndDateMillis = defaultEndMillis
    )

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val startDate: LocalDate = Instant.ofEpochMilli(dateRangePickerState.selectedStartDateMillis ?: Instant.now().toEpochMilli()).atZone(
                    ZoneId.of("UTC")).toLocalDate()

                val endDate: LocalDate = Instant.ofEpochMilli(dateRangePickerState.selectedEndDateMillis ?: Instant.now().toEpochMilli()).atZone(
                    ZoneId.of("UTC")).toLocalDate()

                onDateSelected(startDate, endDate)

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
        DateRangePicker(
            state = dateRangePickerState,
            title = { },
            headline = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Select Registration Period",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Primary,
                            fontSize = 20.sp
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.8f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PLuckTimePicker(
    onTimeSelected: (LocalTime) -> Unit,
    onDismiss: () -> Unit,
    defaultTime: LocalTime
) {
    val timePickerState = rememberTimePickerState()

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(36.dp),
            color = PluckPalette.Surface,
        ) {
            Column {
                TimePicker(
                    modifier = Modifier.padding(24.dp),
                    state = timePickerState
                )

                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text("Cancel")
                    }

                    TextButton(
                        onClick = {
                            onTimeSelected(LocalTime.of(timePickerState.hour, timePickerState.minute))
                            onDismiss()
                        }
                    ) {
                        Text("Ok")
                    }
                }
            }
        }
    }
}
