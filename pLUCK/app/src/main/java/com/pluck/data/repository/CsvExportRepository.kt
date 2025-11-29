package com.pluck.data.repository

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.ui.model.Event
import com.pluck.ui.screens.WaitlistEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Repository for CSV export operations
 *
 * Handles exporting entrant data to CSV files for sharing:
 * - Export all entrants to CSV (US 02.06.05)
 * - Export filtered by status (accepted, selected, etc.)
 * - Generate shareable intents for CSV files
 */
class CsvExportRepository {

    /**
     * Export entrants to CSV file with optional status filtering
     *
     * @param context Android context for file operations
     * @param event Event associated with the entrants
     * @param entrants List of entrants to export
     * @param includeStatus Optional status filter (null exports all)
     * @return Result with shareable Intent or error
     */
    suspend fun exportEntrantsToCSV(
        context: Context,
        event: Event,
        entrants: List<WaitlistEntry>,
        includeStatus: WaitlistStatus? = null
    ): Result<Intent> = withContext(Dispatchers.IO) {
        try {
            // Filter entrants by status if specified
            val filteredEntrants = if (includeStatus != null) {
                entrants.filter { it.status == includeStatus }
            } else {
                entrants
            }

            // Create CSV file
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "${event.title.replace(" ", "_")}_entrants_$timestamp.csv"
            val file = File(context.cacheDir, fileName)

            FileWriter(file).use { writer ->
                // Write CSV header
//                writer.append("User ID,User Name,Status,Position,Joined Date\n")
                writer.append("User ID,User Name,Status,Joined Date\n")

                // Write data rows
                filteredEntrants.forEach { entrant ->
                    writer.append("${entrant.userId},")
                    writer.append("\"${entrant.userName}\",")
                    writer.append("${entrant.status.name},")
//                    writer.append("${entrant.position},")
                    writer.append("${entrant.joinedDate}\n")
                }
            }

            // Create share intent
            val fileUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/csv"
                putExtra(Intent.EXTRA_STREAM, fileUri)
                putExtra(Intent.EXTRA_SUBJECT, "Entrants for ${event.title}")
                putExtra(Intent.EXTRA_TEXT, "Exported entrants data for ${event.title}")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            Result.success(Intent.createChooser(shareIntent, "Export Entrants CSV"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Export only accepted entrants to CSV
     *
     * @param context Android context for file operations
     * @param event Event associated with the entrants
     * @param entrants List of entrants to filter and export
     * @return Result with shareable Intent or error
     */
    suspend fun exportAcceptedEntrants(
        context: Context,
        event: Event,
        entrants: List<WaitlistEntry>
    ): Result<Intent> {
        return exportEntrantsToCSV(context, event, entrants, WaitlistStatus.ACCEPTED)
    }

    /**
     * Export only selected (lottery winners) entrants to CSV
     *
     * @param context Android context for file operations
     * @param event Event associated with the entrants
     * @param entrants List of entrants to filter and export
     * @return Result with shareable Intent or error
     */
    suspend fun exportSelectedEntrants(
        context: Context,
        event: Event,
        entrants: List<WaitlistEntry>
    ): Result<Intent> {
        val invitedOrLegacy = entrants.filter {
            it.status == WaitlistStatus.INVITED || it.status == WaitlistStatus.SELECTED
        }
        return exportEntrantsToCSV(context, event, invitedOrLegacy, null)
    }

    /**
     * Export all entrants regardless of status to CSV
     *
     * @param context Android context for file operations
     * @param event Event associated with the entrants
     * @param entrants List of all entrants to export
     * @return Result with shareable Intent or error
     */
    suspend fun exportAllEntrants(
        context: Context,
        event: Event,
        entrants: List<WaitlistEntry>
    ): Result<Intent> {
        return exportEntrantsToCSV(context, event, entrants, null)
    }
}
