
# ADR-0004: Use Play Asset Delivery for Python distro

## Status
Accepted

## Context
Bundling Python + libs may exceed practical APK/AAB size constraints.

## Decision
Ship Python distro and wheels as a Play Asset Delivery pack.

## Consequences
- Pros: reduces base download size; supports large assets.
- Cons: requires asset extraction step on first run.
