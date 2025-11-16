# ESDE_WORKSHOP_TASK_CICD

> Learn CI (build/test/coverage), Docker CD (build & push), and GitHub Pages (publish a tiny build page).

---

## 🔧 Prerequisites

- Docker installed (Desktop or Engine)
- Git + GitHub account
- Java 17 (local runs only; CI provides JDK)
- Create a Docker Hub account and a Personal Access Token (PAT)


Add a repo variable and secret in your fork
GitHub → your fork → Settings → Secrets and variables → Actions

Variables → New repository variable

Name: DOCKERHUB_USERNAME

Value: <your-dockerhub-username>

Secrets → New repository secret

Name: DOCKERHUB_TOKEN

Value: <your Docker Hub PAT> (create at https://hub.docker.com/settings/security
)
---

## Stats
[![CI](https://img.shields.io/github/actions/workflow/status/janneshatzius/ESDE_WORKSHOP_TASK_CICD/CI.yml?branch=main)](https://github.com/janneshatzius/ESDE_WORKSHOP_TASK_CICD/actions/workflows/CI.yml) 

[![CD](https://img.shields.io/github/actions/workflow/status/janneshatzius/ESDE_WORKSHOP_TASK_CICD/CD.yml?branch=main)](https://github.com/janneshatzius/ESDE_WORKSHOP_TASK_CICD/actions/workflows/CD.yml) 

[![codecov](https://codecov.io/gh/janneshatzius/ESDE_WORKSHOP_TASK_CICD/graph/badge.svg?token=O77MK9D3UC)](https://codecov.io/gh/janneshatzius/ESDE_WORKSHOP_TASK_CICD)
## ▶️ Quick Start: run the prebuilt image (no login needed)

```bash
# Pull (public image)
docker pull ellimen/esd:starter

# If on Apple Silicon and you get a platform error/warning:
docker pull --platform=linux/amd64 ellimen/esd:starter

# Run it
docker run --platform=linux/amd64 --rm -p 8080:8080 -e APP_COMMIT=starter ellimen/esd:starter

# In another terminal, check health:
curl -fsS http://localhost:8080/api/health
curl -fsS http://localhost:8080/api/info
