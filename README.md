# CI/CD Workshop Repository
A **CI/CD pipeline** is an automated process that helps software teams deliver code faster and with fewer errors. **CI (Continuous Integration)** means developers regularly add their code to a shared place, and every time they do, the system automatically builds and tests the code to catch problems early. **CD (Continuous Delivery)** means that after the tests pass, the system automatically prepares the code for release by setting up everything needed to deploy it. Together, CI/CD creates a smooth, reliable flow where new code is checked, tested, and made ready for deployment without manual work, leading to faster releases and better-quality software (Anastasov Marko, 2025).

![CI/CD Pipeline Diagram](Research/images/continuous-delivery-wissen-kompakt-t2informatik.jpg)
(t2informatik)

## Workshop Task Repository
 - [Repo](https://github.com/janneshatzius/ESDE_WORKSHOP_TASK_CICD)
## Table of Content
- [Research](/Research/)
- [Java Project](/src/)
- [Workflow](/.github/workflows/)

## Pipeline Dashboard
<!-- Build & Deploy -->
### CI Test Build
[![CI](https://img.shields.io/github/actions/workflow/status/FontysVenlo/esd-workshop-ci-cd_esde_jannes_elias/CI.yml?branch=main)](https://github.com/FontysVenlo/esd-workshop-ci-cd_esde_jannes_elias/actions/workflows/CI.yml)
<br>
### CD Test Build
[![CD](https://img.shields.io/github/actions/workflow/status/FontysVenlo/esd-workshop-ci-cd_esde_jannes_elias/CD.yml?branch=main)](https://github.com/FontysVenlo/esd-workshop-ci-cd_esde_jannes_elias/actions/workflows/CD.yml)
<br>
### Coverage of Tests
<!-- Coverage (Codecov) – will turn green once Codecov is set below -->
[![codecov](https://codecov.io/gh/FontysVenlo/esd-workshop-ci-cd_esde_jannes_elias/graph/badge.svg?token=O77MK9D3UC)](https://codecov.io/gh/FontysVenlo/esd-workshop-ci-cd_esde_jannes_elias)
<br>
### Docker Pulls
<!-- Docker pulls (optional) -->
[![Docker Pulls](https://img.shields.io/docker/pulls/ellimen/esd)](https://hub.docker.com/r/ellimen/esd)

### Current Pipline flow
[GitHub Actions](https://github.com/FontysVenlo/esd-workshop-ci-cd_esde_jannes_elias/actions)

---
## Sources
t2informatik (-):*Continuous Delivery* [online] 
https://t2informatik.de/wissen-kompakt/continuous-delivery/#:~:text=Continuous%20Delivery%20%E2%80%93%20die%20Perfektionierung%20der,ein%20Fundament%20f%C3%BCr%20Continuous%20Delivery 
[Accessed: 08.11.2025]

Anastasov Marko (2025): *CI/CD Pipeline: A Gentle Introduction* [online]
https://semaphore.io/blog/cicd-pipeline
[Accessed: 16.11.2025]

---

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/t1er-CAW)
