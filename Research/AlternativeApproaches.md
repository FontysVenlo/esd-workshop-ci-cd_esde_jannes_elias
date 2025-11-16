# Alternatives to CI/CD

---

## GitOps
*The following content is fully based on* (GitLab).
### What is GitOps?

GitOps is an operational framework that applies DevOps practices like version control, collaboration, and CI/CD to infrastructure automation**.  
It ensures consistent and repeatable deployments across environments.

While much of the software development lifecycle has been automated, infrastructure management has often remained a manual process requiring specialized teams.  
GitOps solves this by using automation and treating infrastructure configurations as code.

Modern applications are developed for speed and scalability.  
Organizations with a mature DevOps culture can deploy code to production many times per day through practices like version control, code review, and CI/CD pipelines**.  
GitOps extends these ideas to automate the provisioning of modern, cloud-based infrastructure.

### How Teams Use GitOps

Teams implement GitOps by using Git repositories as the single source of truth, automating deployments, and enforcing changes through merge requests (MRs) or pull requests (PRs).

GitOps is not a single product or platform.  
Instead, it is a process that uses tools and workflows to manage configurations and deployments automatically.

#### Core Components of GitOps

1. **Infrastructure as Code (IaC):**  
   All infrastructure configurations are stored in a Git repository.  
   Git tracks every change over time, ensuring visibility and control.

2. **Merge Requests (MRs) / Pull Requests (PRs):**  
   Every infrastructure change is proposed through a merge or pull request, reviewed by the team, and then merged into the main branch.  
   This creates a clear audit trail for all updates.

3. **Continuous Integration / Continuous Delivery (CI/CD):**  
   When new code is merged, the CI/CD pipeline automatically updates the environment.  
   Any manual change or configuration drift is overwritten so the environment matches the desired state defined in Git.

### GitOps Challenges

Adopting GitOps requires **discipline and process change**.  
Teams must resist the temptation to make quick, manual edits in production.  
All changes should go through merge requests to maintain traceability and collaboration.

This workflow introduces a “change by committee” process — which can feel slow at first — but ensures transparency, accountability, and stability.

### GitOps Benefits

- **Improved Efficiency and Security:**  
  Automation reduces manual work and human error.  
  All changes are tracked and reviewed, improving security.

- **Better Collaboration:**  
  Developers and operators use the same Git-based workflow, improving coordination and communication.

- **Faster Deployments:**  
  Automated pipelines deliver updates quickly and consistently.

- **Cost Reduction:**  
  Automation and unified processes reduce time and resource overhead.

GitOps also helps organizations use **containers and microservices** effectively while maintaining consistency across **Kubernetes clusters, Docker images, and cloud instances**.

### GitOps vs DevOps

GitOps is a **modern implementation of DevOps** that uses **Git repositories as the single source of truth** for both application and infrastructure deployments.

- **DevOps** is a cultural and technical movement focused on collaboration, automation, and continuous delivery across all types of applications.
- **GitOps** applies these principles specifically through **Git-based workflows** and is most often used with **containerized environments** like **Kubernetes**.

The main difference:  
**GitOps requires Git to be the definitive source of truth** for deployment states, while DevOps can use multiple tools or approaches.

### GitOps Workflow

A GitOps workflow is a **version-controlled and systematic approach** to managing infrastructure and applications.

#### Key Components

1. **Git Repository:**  
   Stores both application code and configuration, serving as the central source of truth.

2. **Continuous Delivery (CD) Pipeline:**  
   Automates building, testing, and deployment processes, ensuring smooth transitions from development to production.

3. **Application Deployment Tool:**  
   Handles orchestration and management of application resources based on the Git configuration.

4. **Monitoring System:**  
   Tracks performance, gathers feedback, and provides data to maintain system health and reliability.

### Why GitOps Works

GitOps can be applied to **different types of infrastructure** such as virtual machines, containers, or Kubernetes.  
Its power lies in its **developer-centric experience**, where infrastructure management happens in the same **version control system** as application development.  
This increases **collaboration, visibility, and consistency** across the entire system.

---

## Daily / Nightly Builds as Alternatives to CI/CD

### What are Daily and Nightly Builds?
- A Daily Build is an automated build of the latest version of a software system, run typically once per day (Wikipedia, 2025).
- A Nightly Build refers specifically to a build that runs overnight, when developers are unlikely to be working on the code (Wikipedia, 2021).
- Both practices serve to detect integration issues early, ensure the build still works, and make the latest version available for further development or testing (Wikipedia, 2021).

### How do they work?
- The system compiles the latest code, runs some automated tests (often smoke tests) and sends feedback or notifications if the build fails (Wikipedia, 2025).
- With Nightly Builds, the processing time is less critical, since it happens when active development is minimal (Wikipedia, 2021).
- Compared to full CI/CD, the frequency is lower (once daily or nightly) and the feedback loop is slower.

### Benefits
- Early detection of build breakage or integration conflicts, especially in larger teams (t2informatik).
- Provides a reproducible snapshot of the system’s latest state for testing or evaluation (Wikipedia, 2021).
- Simpler to set up than full CI/CD pipelines, especially for smaller teams or legacy code bases (Wikipedia, 2025).

### Limitations
- Does not offer the immediacy of full continuous integration (which may build after every commit) (Wikipedia, 2021).
- Nightly or Daily Builds may still contain untested or unstable features, and thus may not be fit for production (Wikipedia, 2021).
- Because feedback arrives later, issues may accumulate and be harder to trace back to individual changes (Wikipedia, 2021).

### Conclusion
Daily and Nightly Builds are credible alternatives (or precursors) to more advanced CI/CD setups. They help improve integration safety and build visibility without the complexity of full pipelines.  
In an organization where full CI/CD might be too heavy or early, implementing Daily / Nightly Builds can be a meaningful first step towards automation and stability.

---
## The Traditional Approach: Manual Deployment
*The following content is fully based on* (AutoMQ Team, 2025).

### What is it?
Manual deployment means a person or a team does most or all of the work to release software into environments such as staging or production.  
This usually involves using checklists, runbooks, or simple scripts to guide the deployment.

### How Manual Deployment Works
- Developers follow a defined sequence of tasks: copy files, run installation scripts, update configuration settings, restart services often directly on servers or via remote connections.
- It depends a lot on the skills and caution of the people doing the deployment.
- Even though it is “manual”, it might still use simple scripts (shell, batch) for repetitive tasks, but the overall process orchestration remains manual.

### When Manual Deployment Makes Sense
Manual deployment can be acceptable in certain situations:
- Small-scale projects with simple architecture and infrequent releases.
- One-time or special deployments that will not be repeated.
- Legacy systems that are hard to automate or where automation cost is higher than benefit.
- Teams that do not have automation tools or expertise yet.

### Common Problems of Manual Deployment
- **Error-prone:** Humans make mistakes, especially with complex or repetitive steps.
- **Slow and inefficient:** It takes more time than automated methods, delaying releases.
- **Inconsistent environments:** People may perform steps differently, causing configuration drift.
- **Poor scalability:** More servers, components, or frequent releases make manual deployment harder.
- **Difficult rollbacks:** Undoing a bad release is slow and error-prone.
- **Staff stress:** Frequent and high-risk manual deployments can increase pressure and burnout.

### Best Practices (If Manual Deployment Cannot Be Avoided)
- Use **comprehensive checklists** for all deployment procedures.
- Put all scripts and configuration files into **version control**.
- Keep **development, staging, and production environments** as similar as possible.
- Use **peer reviews** to check deployment plans and execution.
- Automate small repetitive tasks, even if the process stays manual.
- Perform **thorough pre- and post-deployment testing**.
- Document everything, including problems and deviations from the plan.

---

## Comparison Table: Alternatives to CI/CD

| Category              | GitOps                                                                                                           | Daily / Nightly Builds                                                                                                                                                         | Manual Deployment                                                                                                               |
|-----------------------|------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
| **Description**       | Git as the single source of truth for infrastructure; deployments managed through merge/pull requests (GitLab).  | Automated builds that run on a fixed schedule (daily or nightly) to compile code and execute automated tests (Wikipedia, 2021,2025).                                           | Deployments are executed manually by people (copying files, updating configurations, restarting services) (AutoMQ Team, 2025).  |
| **Advantages**        | High transparency; automation; increased security; ideal for Kubernetes environments (GitLab).                   | Early detection of integration issues (t2informatik), simple setup, supports long-running tests (especially at night) (Wikipedia, 2021); reproducible state (Wikipedia, 2025). | Easy to start; flexible; suitable for one-off deployments (AutoMQ Team, 2025).                                                  |
| **Disadvantages**     | Requires discipline; review processes may slow things down (GitLab).                                             | Feedback is delayed; issues may accumulate; not suitable for production-quality validation (Wikipedia, 2021).                                                                  | Error-prone; slow; poor scalability; difficult rollback; high stress (AutoMQ Team, 2025).                                       |
| **Typical Use Cases** | Modern cloud/Kubernetes setups; teams with DevOps maturity (GitLab).                                             | Teams without full CI/CD; legacy codebases; projects needing daily snapshots or long overnight tests (Wikipedia, 2021).                                                        | Small projects; special one-time deployments; legacy systems without automation (AutoMQ Team, 2025).                            |

## Sources

GitLab (-): *What is GitOps* [online]  
https://about.gitlab.com/topics/gitops/
[Accessed: 08.11.2025]

Wikipedia (2025): *Daily build* [online]
https://en.wikipedia.org/wiki/Daily_build?
[Accessed: 08.11.2025]

Wikipedia (2021) *Nightly Build* [online]
https://de.wikipedia.org/wiki/Nightly_Build
[Accessed: 08.11.2025]

t2informatik (-) *Nightly Build* [online]
https://t2informatik.de/wissen-kompakt/nightly-build/
[Accessed: 08.11.2025]

AutoMQ Team (2025) *Fully Automated Deployment vs. Manual Deployment: A Comprehensive Guide* [online]
https://www.automq.com/blog/fully-automated-deployment-vs-manual-deployment-comparison
[Accessed: 14.11.2025]
