
#!/usr/bin/env bash
set -euo pipefail

REPO_NAME="bloquinhopy-android"
REMOTE_URL="${1:-}"

git init
git checkout -b main
git add .
git commit -m "chore: initial scaffold"

if [[ -n "$REMOTE_URL" ]]; then
  git remote add origin "$REMOTE_URL"
  git push -u origin main
else
  echo "No remote URL provided."
  echo "Create a GitHub repo (public) and then run:"
  echo "  ./scripts/init_repo.sh https://github.com/<USER>/${REPO_NAME}.git"
fi
