
# ADR-0001: Python runtime via Chaquopy (embedded CPython)

## Status
Accepted

## Context
The app must run Python offline on Android and support native scientific packages (numpy, pandas, matplotlib) and Streamlit.

## Decision
Use Chaquopy to embed CPython in the Android app.

## Consequences
- Pros: offline-first, good performance, supports many native packages.
- Cons: Chaquopy Gradle plugin must be applied to a single module (app), impacting modularization.
- Requires packaging a controlled Python distribution for the MVP.
