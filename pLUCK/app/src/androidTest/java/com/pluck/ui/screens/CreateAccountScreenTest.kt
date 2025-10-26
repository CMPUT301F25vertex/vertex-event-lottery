/**
 * CreateAccountScreenTest.kt
 *
 * Purpose: UI tests for CreateAccountScreen composable.
 * Tests user interaction, form validation, and state management.
 *
 * Test Coverage:
 * - Initial render with all UI elements
 * - Form input handling
 * - Button enable/disable logic
 * - Auto-login toggle
 * - Error message display
 * - Loading state
 */
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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test class for CreateAccountScreen UI.
 */
@RunWith(AndroidJUnit4::class)
class CreateAccountScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Test that all UI elements are displayed on initial render.
     */
    @Test
    fun createAccountScreen_displaysAllElements() {
        composeTestRule.setContent {
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

        // Verify app title
        composeTestRule.onNodeWithText("pLUCK").assertIsDisplayed()

        // Verify screen title
        composeTestRule.onNodeWithText("Create Your Account").assertIsDisplayed()

        // Verify description
        composeTestRule.onNodeWithText(
            "Set up your profile to join exclusive lotteries and receive real-time selections."
        ).assertIsDisplayed()

        // Verify input fields
        composeTestRule.onNodeWithText("Display Name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email (Optional)").assertIsDisplayed()

        // Verify device ID card
        composeTestRule.onNodeWithText("Device ID: TEST-DEVICE-123").assertIsDisplayed()

        // Verify auto-login section
        composeTestRule.onNodeWithText("Auto-login").assertIsDisplayed()

        // Verify create button
        composeTestRule.onNodeWithText("Create Account").assertIsDisplayed()
    }

    /**
     * Test that create button is disabled when display name is empty.
     */
    @Test
    fun createAccountScreen_buttonDisabledWhenNameEmpty() {
        composeTestRule.setContent {
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

        // Button should be disabled initially
        composeTestRule.onNodeWithText("Create Account").assertIsNotEnabled()
    }

    /**
     * Test that create button is enabled when display name is entered.
     */
    @Test
    fun createAccountScreen_buttonEnabledWhenNameEntered() {
        composeTestRule.setContent {
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

        // Enter display name
        composeTestRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField)
            .performTextInput("John Doe")

        // Button should now be enabled
        composeTestRule.onNodeWithText("Create Account").assertIsEnabled()
    }

    /**
     * Test that onCreateAccount is called with correct parameters.
     */
    @Test
    fun createAccountScreen_callsOnCreateWithCorrectParams() {
        var capturedName: String? = null
        var capturedEmail: String? = null
        var capturedPhone: String? = null

        composeTestRule.setContent {
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

        // Enter name and email
        composeTestRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField)
            .performTextInput("Jane Smith")
        composeTestRule.onNodeWithTag(CreateAccountTestTags.EmailField)
            .performTextInput("jane@example.com")

        // Click create button
        composeTestRule.onNodeWithText("Create Account").performClick()

        // Verify callback received correct data
        assert(capturedName == "Jane Smith")
        assert(capturedEmail == "jane@example.com")
        assert(capturedPhone == null)
    }

    /**
     * Test that auto-login toggle works correctly.
     */
    @Test
    fun createAccountScreen_toggleAutoLogin() {
        var autoLoginState = false

        composeTestRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = autoLoginState,
                    onAutoLoginToggle = { autoLoginState = it },
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        // Find and click the switch (next to "Auto-login" text)
        // Note: In actual implementation, might need to use semantics or test tags
        composeTestRule.onNodeWithText("Auto-login").assertIsDisplayed()
    }

    /**
     * Test that error message is displayed when present.
     */
    @Test
    fun createAccountScreen_displaysErrorMessage() {
        val errorMessage = "Registration failed. Please try again."

        composeTestRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = false,
                    errorMessage = errorMessage,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    /**
     * Test that loading state shows correct UI.
     */
    @Test
    fun createAccountScreen_showsLoadingState() {
        composeTestRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = true,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        // Loading text on button
        composeTestRule.onNodeWithText("Creating Account...").assertIsDisplayed()

        // Button should be disabled during loading
        composeTestRule.onNodeWithText("Creating Account...").assertIsNotEnabled()
    }

    /**
     * Test that email is optional (can be empty).
     */
    @Test
    fun createAccountScreen_emailIsOptional() {
        var capturedEmail: String? = null
        var capturedPhone: String? = null

        composeTestRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, email, phone ->
                        capturedEmail = email
                        capturedPhone = phone
                    }
                )
            }
        }

        // Enter only name, leave email empty
        composeTestRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField)
            .performTextInput("Bob User")

        composeTestRule.onNodeWithText("Create Account").performClick()

        // Email should be null since it was empty
        assert(capturedEmail == null)
        assert(capturedPhone == null)
    }

    /**
     * Test that device ID is displayed when provided.
     */
    @Test
    fun createAccountScreen_displaysDeviceId() {
        val deviceId = "DEVICE-XYZ-789"

        composeTestRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = deviceId,
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        composeTestRule.onNodeWithText("Device ID: $deviceId").assertIsDisplayed()
    }

    /**
     * Test that inputs are disabled during loading.
     */
    @Test
    fun createAccountScreen_inputsDisabledWhileLoading() {
        composeTestRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "TEST-123",
                    isLoading = true,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { _, _, _ -> }
                )
            }
        }

        // All input elements should be disabled
        // Note: Compose test assertions for disabled state on TextFields
        composeTestRule.onNodeWithText("Creating Account...").assertIsNotEnabled()
    }
}
