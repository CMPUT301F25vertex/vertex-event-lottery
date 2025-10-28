package com.pluck.ui.theme

/**
 * ThemeManager.kt
 *
 * Purpose: Centralised in-memory registry for the currently selected palette, providing a simple
 * bridge between persisted preferences and Compose theme application.
 *
 * Outstanding Issues: None.
 */
object ThemeManager {
    private var customTheme: CustomTheme? = null
    private var activeThemeId: String = CustomTheme.Blue.id

    /**
     * Retrieves a theme by its ID.
     * Looks in predefined themes.
     *
     * @param themeId The ID of the theme to retrieve
     * @return The CustomTheme with the given ID, or the Blue theme if not found
     */
    fun getThemeById(themeId: String): CustomTheme {
        if (themeId == "custom" && customTheme != null) {
            return customTheme!!
        }
        return CustomTheme.predefinedThemes.find { it.id == themeId }
            ?: customTheme
            ?: CustomTheme.Blue
    }

    /**
     * Gets all available predefined themes.
     *
     * @return List of all available CustomTheme objects
     */
    fun getAllThemes(): List<CustomTheme> = CustomTheme.predefinedThemes

    /**
     * Updates the stored custom theme reference so that it can be applied app-wide.
     *
     * @param theme The custom theme to store, or null to clear
     */
    fun setCustomTheme(theme: CustomTheme?) {
        customTheme = theme
    }

    /**
     * @return Currently stored custom theme, if present.
     */
    fun getCustomTheme(): CustomTheme? = customTheme

    /**
     * Persists the ID of the active theme so callers that rely on the implicit
     * default still render with the most recent selection.
     */
    fun setActiveThemeId(themeId: String) {
        activeThemeId = themeId
    }

    /**
     * @return ID of the currently applied theme, falling back to the default palette.
     */
    fun getActiveThemeId(): String = activeThemeId
}
