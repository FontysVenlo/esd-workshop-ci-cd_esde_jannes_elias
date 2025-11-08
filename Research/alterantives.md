# Alternatives to CI/CD

//TODO more alternatives and comparison
---

## GitOps

### What is GitOps?

GitOps is an operational framework that applies **DevOps practices** like version control, collaboration, and CI/CD to **infrastructure automation**.  
It ensures **consistent and repeatable deployments** across environments.

While much of the software development lifecycle has been automated, **infrastructure management** has often remained a manual process requiring specialized teams.  
GitOps solves this by using automation and treating infrastructure configurations as code.

Modern applications are developed for **speed and scalability**.  
Organizations with a mature DevOps culture can deploy code to production many times per day through practices like **version control, code review, and CI/CD pipelines**.  
GitOps extends these ideas to automate the provisioning of modern, cloud-based infrastructure.

### How Teams Use GitOps

Teams implement GitOps by using **Git repositories as the single source of truth**, automating deployments, and enforcing changes through **merge requests (MRs)** or **pull requests (PRs)**.

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


