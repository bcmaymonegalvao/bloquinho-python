
# PRD — BloquinhoPy

## 1. Summary
BloquinhoPy is an offline-first Python IDE for Android focused on a lightweight notebook experience (.ipynb), with built-in scientific stack and optional GitHub sync.

## 2. Goals (MVP)
- Offline execution of Python scripts and notebooks.
- Notebook UX: open/edit/save .ipynb, run cell/run all, persistent state.
- Rich outputs:
  - stdout/stderr
  - exceptions with readable trace
  - matplotlib images rendered and displayed
  - pandas tables displayed (simple HTML/Markdown rendering)
- Built-in packages available offline: numpy, pandas, matplotlib, streamlit.
- Streamlit runner: start/stop local server and render via in-app WebView.
- GitHub: OAuth login + clone/pull/commit/push for a repo workspace.
- Controlled package installation (safe mode): allowlisted sources only.

## 3. Non-goals (MVP)
- Full Jupyter server (kernelspec, nbextensions, multi-kernel).
- “pip install anything” (unrestricted remote installs are a security/policy risk).
- Multi-ABI support (MVP is arm64-v8a only).

## 4. Target users
- Students learning Python using notebooks on mobile.
- Developers who need quick offline experimentation and GitHub-backed projects.

## 5. Key user journeys
1) Create/open a workspace -> create notebook -> run cell -> see output -> save.
2) Clone GitHub repo -> open notebook -> edit -> commit & push.
3) Run a Streamlit app in workspace -> preview in WebView -> stop server.

## 6. Functional requirements
### Notebooks
- Must load/save .ipynb without corruption.
- Must execute cells sequentially in a shared context.
- Must support code + markdown cells.
- Must support interrupt/stop execution (best-effort for MVP).

### Runtime
- Embedded CPython via Chaquopy.
- All MVP packages must work offline.

### Projects/files
- Workspace file browser (create/rename/delete).
- Import/export via Android SAF.

### Streamlit
- Start/stop local server on localhost.
- WebView preview with reload.
- Expose logs.

### GitHub
- OAuth login and token storage.
- Clone repository into workspace.
- Pull, commit, and push.

### Packages (safe mode)
- Search packages online.
- Install only allowlisted packages/sources with integrity checks.

## 7. Non-functional requirements
- Offline-first: all core features work without network.
- Performance: no ANR while running cells (use background threads/coroutines).
- Storage: cached outputs and images must be bounded and clearable.
- Security:
  - no secrets hardcoded
  - secure token storage
  - restrict dynamic code loading and remote installs
- Release: Play Store via App Bundle + Play Asset Delivery.

## 8. Constraints and assumptions
- MVP targets arm64-v8a only.
- Use JDK 17 toolchain.

## 9. Risks & mitigations
- App size: use Play Asset Delivery for Python distribution.
- Streamlit UX on mobile: provide recommended templates and a “mobile-friendly” preview mode.
- Package installation: keep allowlist + controlled wheelhouse.
