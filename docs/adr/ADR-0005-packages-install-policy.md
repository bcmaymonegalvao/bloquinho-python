
# ADR-0005: Package installation policy (safe mode)

## Status
Accepted

## Decision
MVP ships core packages bundled.
Online installation is limited to:
- allowlisted pure-python packages from trusted sources, and/or
- controlled wheelhouse (our repository) with hash verification

## Consequences
- Pros: reduces security/policy risk.
- Cons: not a full "pip install anything" experience.
