package com.pluck.userstories

import android.util.Log
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluck.ui.screens.ProfileScreen
import com.pluck.ui.theme.PluckTheme
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class EditInfoTest {

    @get:Rule
    val composeRule = createComposeRule()

    // US 01.02.02 – Entrant updates profile information
    @Test
    fun test_update_profile_info() {
        var newName = ""
        var newEmail: String? = null
        var newPhone: String? = null

        composeRule.setContent {
            PluckTheme {
                ProfileScreen(
                    userName = "testName",
                    userEmail = "test@email.ca",
                    userPhone = "111-555-1234",
                    profileImageUrl = null,
                    deviceId = "TEST-DEVICE-123",
                    isLoading = false,
                    onUpdateProfile = { name, email, phone -> {
                        Log.e("CUSTOM_ERROR","Callbadk")

                        newName = name
                        newEmail = email
                        newPhone = phone
                    } },
                    onSignOut = { },
                    onDeleteAccount = { }
                )
            }
        }

        composeRule.onNodeWithText("Edit").performClick()

        composeRule.onNodeWithText("Display Name").performTextInput("NEW")
        composeRule.onNodeWithText("Email").performTextInput("NEW")
        composeRule.onNodeWithText("Phone (optional)").performTextInput("NEW")

        composeRule.onNodeWithText("Save Changes").performClick()

        sleep(5000)

        Log.e("CUSTOM_ERROR", newName)
        Log.e("CUSTOM_ERROR", newEmail.toString())
        Log.e("CUSTOM_ERROR", newPhone.toString())

        assertEquals("NEWtestName", newName)
        assertEquals("NEWtest@email.ca", newEmail)
        assertEquals("NEW111-555-1234", newPhone)
    }
}
