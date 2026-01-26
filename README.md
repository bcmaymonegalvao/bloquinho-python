
# BloquinhoPy — Python Notebooks IDE for Android

BloquinhoPy is an offline-first Python IDE for Android focused on a simple notebook experience (.ipynb).
It ships with an embedded Python runtime and a curated scientific stack for mobile.

## MVP Goals
- Run Python scripts offline (CPython embedded)
- Open, edit and run Jupyter notebooks (.ipynb) without a Jupyter server
- Built-in: numpy, pandas, matplotlib, streamlit (arm64-v8a MVP)
- Streamlit runner (localhost) rendered inside an in-app WebView
- GitHub login + clone/pull/push workflows
- Controlled package installation (safe + allowlisted)

## Tech Stack
- Kotlin + Jetpack Compose + Material 3
- MVVM + Coroutines/Flow + Hilt
- Embedded Python runtime (Chaquopy)
- Play Asset Delivery for Python distribution

## Getting started
### Prerequisites
- Android Studio (latest stable)
- JDK 17

### Build
Open the project in Android Studio and run the `app` configuration.

> Note: CI workflows expect the Gradle Wrapper (`gradlew`). If missing, generate it locally:
> `gradle wrapper --gradle-version 8.11.1` (or via Android Studio) and commit the generated files.

## Project Docs
- Product requirements: `docs/PRD.md`
- Architecture: `docs/ARCHITECTURE.md`
- Architecture decisions: `docs/adr/`

## License
MIT — see `LICENSE`.
