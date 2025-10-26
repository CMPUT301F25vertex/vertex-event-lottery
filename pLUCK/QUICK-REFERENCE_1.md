# VertexLotto Implementation – Quick Reference (v1.2)

## Current Feature Set
- **CreateAccountScreen** – first-time registration with name + optional email/phone.
- **WelcomeBackScreen** – returning-user welcome card with auto-login toggle.
- **HomeScreen** – event list with Material bottom navigation.
- **NotificationsScreen** – Figma-inspired notification center with segmented control and rich cards.
- **BottomNavBar** – five-tab navigation (Home, Events, Notifications, Settings, Profile).
- **PLuckNavHost** – centralised navigation controller handling login → home flows.

## Automated Test Suites
| Suite | Location | Count |
|-------|----------|-------|
| JVM unit tests | `app/src/test/java/com/pluck/...` | **16** (`NavTabTest`, `NotificationFilterTest`) |
| Instrumented Compose tests | `app/src/androidTest/java/com/pluck/...` | **16** (`CreateAccountScreenTest`, `UserStoryUiTest`, `NotificationsScreenTest`) |
| **Total** |  | **32 tests** |

> **Instrumented-test note:** AGP 8.1’s Unified Test Platform currently crashes when invoked via `connectedDebugAndroidTest`. Use the manual sequence below instead.

```bash
cd dev/pLUCK
./gradlew assembleDebug                # build app
./gradlew testDebugUnitTest            # JVM unit tests
./gradlew installDebug installDebugAndroidTest
"$LOCALAPPDATA/Android/Sdk/platform-tools/adb" shell \
  am instrument -w com.pluck.test/androidx.test.runner.AndroidJUnitRunner
```
Adjust the `adb` path if your SDK lives elsewhere.

## Source Layout
```
app/src/main/java/com/pluck/
├── data/…
├── navigation/PLuckNavHost.kt
├── ui/components/BottomNavBar.kt
├── ui/model/NotificationModels.kt
└── ui/screens/
    ├── CreateAccountScreen.kt
    ├── HomeScreen.kt
    ├── NotificationsScreen.kt
    ├── ProfileScreen.kt
    ├── WelcomeBackScreen.kt
    └── PlaceholderScreen.kt  (used for Settings/Admin stubs)

app/src/test/java/com/pluck/
├── ui/components/NavTabTest.kt
└── ui/model/NotificationFilterTest.kt

app/src/androidTest/java/com/pluck/
├── UserStoryUiTest.kt
└── ui/screens/
    ├── CreateAccountScreenTest.kt
    └── NotificationsScreenTest.kt
```

## User Flows
### New User
1. Launch app → **CreateAccountScreen**
2. Enter display name (required) + optional email/phone
3. Toggle auto-login if desired
4. Tap **Create Account** → **HomeScreen**

### Returning User
1. Launch app → **WelcomeBackScreen**
2. Review device ID / auto-login setting
3. Tap **Continue** → **HomeScreen**

### Notifications
- Segmented control defaults to **Unread** and displays the unread count.
- Cards show icon, headline, subtext, and tailored call-to-action buttons (Event Details / Accept / Decline).
- Switching to **Read** reveals archived notifications.

## Common Modification Points
- **Update nav colours / icons:** `BottomNavBar.kt`
- **Add navigation tabs:** extend `NavTabs` in `BottomNavBar.kt` and update `PLuckNavHost`
- **Adjust notification sample data:** `NotificationModels.kt` (`previewNotifications()`)
- **Change segmentation behaviour:** `NotificationFilter` / `filterBy` in `NotificationModels.kt`

## Supporting Docs
- `IMPLEMENTATION_COMPLETE.md` – full change log & rationale.
- `TEST_DOCUMENTATION.md` – detailed test descriptions, execution steps, and known issues.
- Inline KDoc headers (purpose, design pattern, outstanding issues) in every source file.

---
**Last updated:** 2025‑10‑24 • **Maintainer:** VertexLotto dev team • **Build:** ✅ `./gradlew assembleDebug`
