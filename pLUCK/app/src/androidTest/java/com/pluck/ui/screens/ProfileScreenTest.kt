package com.pluck.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluck.ui.theme.PluckTheme
import org.junit.Assert.assertEquals
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

        composeRule.onNodeWithTag(ProfileScreenTestTags.ScrollContainer).assertExists()

        composeRule.onNodeWithText("Register as Admin", useUnmergedTree = true).assertExists()
        assertThrows(AssertionError::class.java) {
            composeRule.onNodeWithText("Admin Dashboard", useUnmergedTree = true).assertExists()
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

        composeRule.onNodeWithTag(ProfileScreenTestTags.ScrollContainer).assertExists()

        composeRule.onNodeWithText("Admin Dashboard", useUnmergedTree = true).assertExists()
        assertThrows(AssertionError::class.java) {
            composeRule.onNodeWithText("Register as Admin", useUnmergedTree = true).assertExists()
        }
    }

    @Test
    fun deleteAccount_showsConfirmationDialogAndInvokesCallback() {
        var deleteCount = 0

        composeRule.setContent {
            PluckTheme {
                ProfileScreen(
                    userName = "Avery",
                    userEmail = "avery@example.com",
                    userPhone = "555-8888",
                    profileImageUrl = null,
                    deviceId = "device-delete",
                    isLoading = false,
                    onSignOut = {},
                    onDeleteAccount = { deleteCount += 1 }
                )
            }
        }

        composeRule.onNodeWithTag(ProfileScreenTestTags.ScrollContainer).assertExists()

        composeRule.onNodeWithText("Delete Account", useUnmergedTree = true)
            .assertExists()
            .performClick()

        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithText("Delete account?").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Delete account?").assertExists()

        composeRule.onNodeWithTag(ProfileScreenTestTags.DeleteConfirmButton)
            .performClick()

        assertEquals("Delete callback should be invoked once.", 1, deleteCount)
    }
}
