package com.pluck

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DebugTest {
    @Test
    fun debugTest() {
        // If this test fails, set DEBUG to false in the file Debug.kt
        // Debug must be disabled in the main branch, when enabled it creates a debug menu which
        // is designed to only be visible during development, and grants way to many abilities to all
        // users. The debug flag also enables toasts, which are great for development, but kind of
        // ugly in production.
        assert(!DEBUG)
    }
}
