
# ADR-0002: Notebook engine without running a Jupyter server

## Status
Accepted

## Context
We need notebook UX on mobile with minimal complexity and offline execution.

## Decision
Implement a lightweight .ipynb engine:
- parse/save JSON (.ipynb)
- execute cells sequentially in a shared context
- capture stdout/stderr, exceptions, and rich outputs (images/tables)

## Consequences
- Pros: simpler than shipping Jupyter, better mobile UX, less overhead.
- Cons: we must implement output adapters (matplotlib, pandas display).
