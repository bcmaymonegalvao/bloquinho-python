
# ADR-0006: Run Streamlit locally and render in WebView

## Status
Accepted

## Decision
Start Streamlit in embedded Python on localhost and open it inside WebView.

## Consequences
- Pros: minimal integration effort; works offline for local apps.
- Cons: UX depends on Streamlit app layout; background process management needed.
