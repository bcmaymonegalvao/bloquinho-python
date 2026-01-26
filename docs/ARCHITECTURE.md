
# Architecture — BloquinhoPy

## 1. Principles
- Offline-first
- Replaceable runtime boundary (PythonRuntime interface)
- Clear separation: UI / domain / data
- Deterministic execution and explicit logs

## 2. Module layout
- :app
  - Compose UI, navigation, DI, Chaquopy integration (ONLY module with Chaquopy plugin)
- :core
  - storage, logging, utils, design system
- :feature-notebooks
  - notebook editor/viewer, notebook state, outputs UI
- :feature-projects
  - workspace explorer, templates, SAF import/export
- :feature-github
  - OAuth flow, Git operations, repo browser
- :runtime-python
  - interfaces + Kotlin glue (no Chaquopy plugin)
- :python-pack
  - Play Asset Delivery pack (python distro, wheels)

## 3. Key interfaces
### PythonRuntime
- exec(code, workingDir) -> ExecResult(stdout, stderr, exitCode)
- interrupt()

### NotebookEngine
- load/save .ipynb
- runCell(index)
- runAll()
- output adapters (text, error, image, table)

### StreamlitRunner
- start(entryFile, port)
- stop()
- status/logs

### GitProvider
- login/logout
- listRepos
- clone/pull/commit/push

## 4. Execution flows
### Notebook run flow
1) UI triggers runCell
2) NotebookEngine builds code snippet + execution context
3) PythonRuntime executes
4) Captures:
   - stdout/stderr
   - exception trace
   - matplotlib outputs rendered to PNG in cache dir
   - pandas table serialized (HTML/Markdown)
5) UI renders outputs

### Streamlit flow
1) User selects app.py in workspace
2) StreamlitRunner launches: `python -m streamlit run app.py` on 127.0.0.1:PORT
3) WebView opens http://127.0.0.1:PORT
4) Stop button terminates server and frees port

### Package install flow (safe mode)
- Online search is allowed
- Installation is restricted to allowlisted sources
- Prefer local wheelhouse for native packages
- Store installs in app-private dir
- Keep audit log
