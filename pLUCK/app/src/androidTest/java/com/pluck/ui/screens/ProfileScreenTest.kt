package com.pluck.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluck.ui.theme.PluckTheme
import org.junit.Assert.assertThrows
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun nonAdminProfile_showsRegistrationCallToAction() {
        composeRule.setContent {
            PluckTheme {
                ProfileScreen(
                    userName = "Jordan",
                    userEmail = "jordan@example.com",
                    userPhone = "555-1234",
                    profileImageUrl = null,
                    deviceId = "device-xyz",
                    isLoading = false,
                    isUpdatingProfile = false,
                    isAdmin = false,
                    onUpdateProfile = { _, _, _ -> },
                    onSignOut = {},
                    onDeleteAccount = {},
                    onRegisterAdmin = {}
                )
            }
        }

        composeRule.onNodeWithText("Register as Admin").assertIsDisplayed()
        assertThrows(AssertionError::class.java) {
            composeRule.onNodeWithText("Admin Dashboard").assertIsDisplayed()
        }
    }

    @Test
    fun adminProfile_showsDashboardShortcut() {
        composeRule.setContent {
            PluckTheme {
                ProfileScreen(
                    userName = "Caiden",
                    userEmail = "caiden@example.com",
                    userPhone = null,
                    profileImageUrl = null,
                    deviceId = "device-admin",
                    isLoading = false,
                    isUpdatingProfile = false,
                    isAdmin = true,
                    onUpdateProfile = { _, _, _ -> },
                    onSignOut = {},
                    onDeleteAccount = {},
                    onAdminDashboard = {},
                    onRegisterAdmin = {}
                )
            }
        }

        composeRule.onNodeWithText("Admin Dashboard").assertIsDisplayed()
        assertThrows(AssertionError::class.java) {
            composeRule.onNodeWithText("Register as Admin").assertIsDisplayed()
        }
    }
}
