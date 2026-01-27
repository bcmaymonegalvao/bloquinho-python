# GitHub Releases Setup - BloquinhoPy

This document explains how to configure and use GitHub Releases for distributing APK builds of the BloquinhoPy Android application.

## Overview

Instead of publishing to Google Play Store, we distribute APK files directly through GitHub Releases. This provides:
- Direct control over release distribution
- No Play Store review process
- Faster release cycles
- Full transparency of release history
- Automatic APK generation via GitHub Actions

## Automated Build Pipeline

The GitHub Actions workflow (`build-release.yml`) automatically:
1. Triggers on version tags (e.g., `v1.0.0`)
2. Sets up Java and Gradle environment
3. Builds release APK and AAB files
4. Signs the APK with release keystore
5. Creates a GitHub Release with the APK
6. Uploads installation instructions

## Prerequisites

### 1. Android Keystore Setup

Create a release keystore for signing APKs:

```bash
keytool -genkey -v -keystore release.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias bloquinhopy-key
```

When prompted, enter:
- Password (remember this!)
- Key name: `bloquinho-python-release`
- Organization: Your name/organization
- Country: US (or your country code)

### 2. GitHub Secrets Configuration

Add the following secrets to your repository (Settings > Secrets and variables > Actions):

#### `KEYSTORE_B64`
Encode your keystore.jks as base64:

```bash
base64 -i release.keystore | tr -d '\n' | pbcopy
```

Create secret `KEYSTORE_B64` and paste the output.

#### `KEYSTORE_PASSWORD`
The password you used when creating the keystore.

#### `KEY_ALIAS`
The alias used when creating the keystore (e.g., `bloquinhopy-key`).

#### `KEY_PASSWORD`
The password for the keystore entry (usually same as KEYSTORE_PASSWORD).

## Creating a Release

### Method 1: Using Git Tags (Recommended)

```bash
# Create a local tag
git tag -a v1.0.0 -m "Release version 1.0.0"

# Push the tag to GitHub
git push origin v1.0.0
```

The GitHub Actions workflow will automatically:
- Build the APK
- Create a GitHub Release
- Upload the APK file
- Add installation instructions

### Method 2: Manual Workflow Trigger

1. Go to Actions tab in your repository
2. Select "Build and Release APK" workflow
3. Click "Run workflow"
4. GitHub will build and create artifacts

## Release Tags Format

Use semantic versioning for release tags:

- `v1.0.0` - Major.minor.patch format
- `v1.0.0-beta` - Beta releases
- `v1.0.0-rc1` - Release candidate

The workflow will match any tag starting with `v`.

## Installation Instructions

### For Users

1. Navigate to the [Releases page](https://github.com/bcmaymonegalvao/bloquinho-python/releases)
2. Download the latest `app-release.apk`
3. Transfer to your Android device
4. Go to Settings > Security > Unknown Sources (enable if needed)
5. Open the APK file and tap Install
6. Grant permissions when prompted
7. Launch the app from your app drawer

### Via ADB (Advanced)

```bash
adb install -r app-release.apk
```

## Troubleshooting

### APK Won't Install
- Ensure Android 8.0+ (API level 26)
- Check "Install from Unknown Sources" is enabled
- Try clearing app cache before reinstalling

### Build Fails
- Verify Gradle build works locally: `./gradlew assembleRelease`
- Check Java version (must be 17+)
- Ensure all Gradle plugins are up to date

### Workflow Doesn't Trigger
- Verify tag format matches `v*` pattern
- Check GitHub Actions is enabled in repository settings
- Review workflow logs in Actions tab

## Release Notes Template

When creating a release, include:

```markdown
# BloquinhoPy v1.0.0

## New Features
- Feature 1
- Feature 2

## Bug Fixes
- Bug fix 1
- Bug fix 2

## System Requirements
- Android 8.0+
- 100MB free storage

## Installation
See the [Installation Instructions](#installation-instructions) in the release body or [README.md](https://github.com/bcmaymonegalvao/bloquinho-python#installation)
```

## Monitoring Releases

Track all releases at: https://github.com/bcmaymonegalvao/bloquinho-python/releases

Each release includes:
- Signed APK file
- AAB (Android App Bundle) file
- Installation instructions
- Changelog reference

## Security Best Practices

1. **Never commit keystore files** - Keep `release.keystore` in `.gitignore`
2. **Rotate keystores periodically** - Consider creating new keystores yearly
3. **Protect secrets** - Limit who can access GitHub Secrets
4. **Verify APKs** - Test on multiple Android versions before release
5. **Keep backups** - Store a backup of your keystore and passwords securely

## Version Management

Update version in `app/build.gradle.kts`:

```kotlin
android {
  defaultConfig {
    versionCode = 1  // Increment for each release
    versionName = "1.0.0"  // Semantic versioning
  }
}
```

## Next Steps

1. Set up secrets in GitHub repository
2. Create your first release tag
3. Monitor the build in Actions tab
4. Verify APK appears in Releases
5. Share release link with users

## Support

For issues or questions about the release process, open an issue on the GitHub repository.
