
# ADR-0003: Support only arm64-v8a in MVP

## Status
Accepted

## Context
Embedded Python + native libs increase binary size and build complexity.

## Decision
Release MVP with arm64-v8a only.

## Consequences
- Pros: smaller size, fewer variants, faster CI.
- Cons: no support for 32-bit devices in MVP.
