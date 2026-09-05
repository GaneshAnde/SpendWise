# Fix Hilt IllegalStateException in MainActivity

The application is crashing because `MainActivity` is incorrectly annotated with `@HiltAndroidApp` instead of `@AndroidEntryPoint`. `@HiltAndroidApp` is intended for the `Application` class, while `@AndroidEntryPoint` is required for Android components (like Activities, Fragments, etc.) to enable Hilt dependency injection.

## User Review Required

> [!NOTE]
> This change is straightforward and corrects a misconfiguration in Hilt setup. The `Application` class (`SplitWiseApplication`) is already correctly annotated with `@HiltAndroidApp` and registered in the manifest.

## Proposed Changes

### Core Component

#### [MODIFY] [MainActivity.kt](file:///Users/ganeshande/Documents/AndStd/SpendWise/app/src/main/java/com/ganesh/spendwise/MainActivity.kt)

- Replace `@HiltAndroidApp` with `@AndroidEntryPoint`.
- Update imports to include `dagger.hilt.android.AndroidEntryPoint` and remove `dagger.hilt.android.HiltAndroidApp`.

## Verification Plan

### Automated Tests
- Run the application to verify that the `IllegalStateException` no longer occurs when navigating to screens that use `hiltViewModel()`.

### Manual Verification
- Deploy the app to a device/emulator.
- Ensure the Dashboard screen loads correctly (which uses `DashboardViewModel` via `hiltViewModel()`).
