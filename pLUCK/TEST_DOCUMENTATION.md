# VertexLotto Test Documentation (2025‑10‑24)

This document summarises the **currently active** automated tests. Older returning-user slider, navigation-flow, and intent suites were removed during the Compose migration; only the tests listed below ship with the project today.

## Test Inventory
| Category | Location | Tests | Notes |
|----------|----------|-------|-------|
| JVM unit tests | `app/src/test/java/com/pluck/ui/components/NavTabTest.kt`<br>`app/src/test/java/com/pluck/ui/model/NotificationFilterTest.kt` | **16** | Nav tab metadata + notification filtering helpers. |
| Compose instrumented UI tests | `app/src/androidTest/java/com/pluck/ui/screens/CreateAccountScreenTest.kt`<br>`app/src/androidTest/java/com/pluck/ui/screens/NotificationsScreenTest.kt` | **13** | Registration flow + notifications UI logic. |
| Compose instrumented user-story tests | `app/src/androidTest/java/com/pluck/UserStoryUiTest.kt` | **3** | Core welcome/back-to-home journeys. |
| **Total** |  | **32 tests** | 16 JVM + 16 instrumented. |

## Directory Structure
```
app/
├── src/
│   ├── main/java/com/pluck/...          # Production sources
│   ├── test/java/com/pluck/...          # JVM unit tests
│   └── androidTest/java/com/pluck/...   # Instrumented Compose tests
```

## Test Details
### NavTabTest (13 JVM tests)
- Ensures every navigation tab exposes the correct ID, label, icon, and route.
- Verifies `NavTabs.all` ordering and uniqueness.
- Exercises data-class equality/copy semantics for tabs.

### NotificationFilterTest (3 JVM tests)
- Validates the pure `filterBy` helper for unread/read segmentation.
- Confirms filtering preserves list ordering and correct membership.

### CreateAccountScreenTest (10 instrumented tests)
- Renders all registration UI elements (branding, fields, cards, toggle, button).
- Validates button enable/disable logic, error state, loading state, and callback data.

### NotificationsScreenTest (3 instrumented tests)
- Confirms unread filter is selected by default and renders unread cards.
- Verifies switching to the read tab hides unread cards and reveals read ones.
- Checks that events with details expose the Event Details button.

### UserStoryUiTest (3 instrumented tests)
- Simulates successful registration and transition to Home.
- Validates welcome-back screen content and navigation.
- Ensures home screen tab navigation callbacks fire.

## Running the Tests
All commands assume the working directory is `dev/pLUCK`.

### JVM Unit Tests
```bash
./gradlew testDebugUnitTest
```

### Instrumented Tests (emulator required)
> **Heads up:** AGP 8.1’s Unified Test Platform currently crashes when invoked via `connectedDebugAndroidTest`. Use the manual sequence below.
```bash
./gradlew installDebug installDebugAndroidTest
"$LOCALAPPDATA/Android/Sdk/platform-tools/adb" shell \
  am instrument -w com.pluck.test/androidx.test.runner.AndroidJUnitRunner
```
If `adb` is not on your `PATH`, substitute the full path to your SDK’s `platform-tools/adb` binary.

### Full Test Sweep (manual)
```bash
./gradlew testDebugUnitTest
./gradlew installDebug installDebugAndroidTest
"$LOCALAPPDATA/Android/Sdk/platform-tools/adb" shell \
  am instrument -w com.pluck.test/androidx.test.runner.AndroidJUnitRunner
```

## Known Issues
- **Studio “Debug tests” action** – still crashes because UTP injects the coroutines debug agent. Until the tooling is patched, rely on the manual instrumentation command above or share a custom run configuration with the team.
- **Legacy docs** – remove or update references to deleted suites (ReturningUserScreenTest, NavigationFlowTest, UserStoryIntentTest) in any external documentation.

## Adding New Tests
1. Place JVM tests under `app/src/test/java/com/pluck/...` and keep packages aligned with the production code.
2. Place Compose/UI tests under `app/src/androidTest/java/com/pluck/...`. Reuse existing test tags (`NotificationsTestTags`, etc.) when possible.
3. Update this document **and** `QUICK-REFERENCE_1.md` with new counts and instructions.
4. Run `testDebugUnitTest` and the manual instrumentation command before committing.

## Support
- Primary reference: this document + `QUICK-REFERENCE_1.md`
- Implementation summary: `IMPLEMENTATION_COMPLETE.md`
- For troubleshooting, consult inline KDoc headers or reach out to the module owner.

---
**Maintainer:** VertexLotto Dev Team • **Last updated:** 2025‑10‑24
