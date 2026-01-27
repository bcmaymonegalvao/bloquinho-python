# GitHub Releases Distribution - Quick Reference

BloquinhoPy APK distribution is handled automatically through GitHub Releases and GitHub Actions.

## Quick Start

### 1. Create Your First Release

```bash
# Create and push a version tag
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

The GitHub Actions workflow will automatically:
- Build the release APK
- Create a GitHub Release
- Upload the APK file
- Add installation instructions

### 2. Download & Install

**For Users:**
1. Go to [Releases](https://github.com/bcmaymonegalvao/bloquinho-python/releases)
2. Download `app-release.apk`
3. On your Android device:
   - Settings > Security > Enable "Install from Unknown Sources"
   - Open the APK file and tap Install

## Workflow Files

- **GitHub Actions**: `.github/workflows/build-release.yml`
  - Triggers on version tags (`v*`)
  - Builds and signs APK
  - Creates GitHub Release
  - Uploads APK automatically

## Secrets Configuration

For automated APK signing, configure these GitHub repository secrets:

- `KEYSTORE_B64`: Base64-encoded keystore file
- `KEYSTORE_PASSWORD`: Keystore password
- `KEY_ALIAS`: Key alias in keystore
- `KEY_PASSWORD`: Key password

See [RELEASES_SETUP.md](../RELEASES_SETUP.md) for detailed setup instructions.

## Release Notes Template

```markdown
# BloquinhoPy v1.0.0

## What's New
- Feature 1
- Feature 2

## Bug Fixes
- Bug fix 1
- Bug fix 2

## Requirements
- Android 8.0+
- 100MB storage
```

## Useful Links

- [Full Setup Guide](../RELEASES_SETUP.md)
- [Deployment Checklist](DEPLOYMENT.md)
- [Changelog](../CHANGELOG.md)
- [Releases Page](https://github.com/bcmaymonegalvao/bloquinho-python/releases)

## Troubleshooting

### Workflow doesn't trigger
- Verify tag format: must start with `v` (e.g., `v1.0.0`)
- Check Actions tab for error logs
- Ensure secrets are configured

### APK won't install
- Android version must be 8.0+
- Enable "Install from Unknown Sources"
- Try uninstalling old version first

## See Also

- Architecture: [ARCHITECTURE.md](ARCHITECTURE.md)
- Roadmap: [ROADMAP.md](ROADMAP.md)
- Deployment: [DEPLOYMENT.md](DEPLOYMENT.md)
