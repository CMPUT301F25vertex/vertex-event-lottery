package com.pluck.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluck.ui.theme.PluckTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// US 01.02.01 – Entrant provides personal information
// US 01.07.01 – Entrant identified by device (no login required)
@RunWith(AndroidJUnit4::class)
class CreateAccountScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun initialRender_displaysFormAndDeviceInfo() {
        composeRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-DEVICE-123",
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        composeRule.onNodeWithText("pLUCK").assertIsDisplayed()
        composeRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField).assertIsDisplayed()
        composeRule.onNodeWithTag(CreateAccountTestTags.EmailField).assertIsDisplayed()
        composeRule.onNodeWithTag(CreateAccountTestTags.PhoneField).assertIsDisplayed()
        composeRule.onNodeWithText("Device ID: TEST-DEVICE-123").assertIsDisplayed()
        composeRule.onNodeWithText("Create Account").assertIsDisplayed().assertIsNotEnabled()
    }

    @Test
    fun createAccount_enablesSubmitWhenNameProvided() {
        composeRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        composeRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField)
            .performTextInput("Jordan")

        composeRule.onNodeWithText("Create Account").assertIsEnabled()
    }

    @Test
    fun submit_trimsOptionalInputsBeforeDispatch() {
        var capturedName: String? = null
        var capturedEmail: String? = null
        var capturedPhone: String? = null

        composeRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { name, email, phone ->
                        capturedName = name
                        capturedEmail = email
                        capturedPhone = phone
                    }
                )
            }
        }

        composeRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField)
            .performTextInput("  Jordan  ")
        composeRule.onNodeWithTag(CreateAccountTestTags.EmailField)
            .performTextInput("  jordan@example.com  ")
        composeRule.onNodeWithTag(CreateAccountTestTags.PhoneField)
            .performTextInput("  555-0100 ")
        composeRule.onNodeWithText("Create Account").performClick()

        composeRule.runOnIdle {
            assertEquals("Jordan", capturedName)
            assertEquals("jordan@example.com", capturedEmail)
            assertEquals("555-0100", capturedPhone)
        }
    }

    @Test
    fun autoLoginToggle_invokesCallback() {
        var toggled = false

        composeRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = { toggled = it },
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        composeRule.onNodeWithTag(CreateAccountTestTags.AutoLoginToggle).performClick()

        composeRule.runOnIdle {
            assertEquals(true, toggled)
        }
    }

    @Test
    fun loadingState_disablesInputsAndShowsProgressText() {
        composeRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = true,
                    errorMessage = null,
                    autoLoginEnabled = true,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        composeRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField).assertIsNotEnabled()
        composeRule.onNodeWithTag(CreateAccountTestTags.AutoLoginToggle).assertIsNotEnabled()
        composeRule.onNodeWithText("Creating Account...").assertIsDisplayed().assertIsNotEnabled()
    }

    @Test
    fun errorMessage_isRenderedWhenProvided() {
        composeRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = false,
                    errorMessage = "Registration failed. Please try again.",
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        composeRule.onNodeWithText("Registration failed. Please try again.").assertIsDisplayed()
    }
}
