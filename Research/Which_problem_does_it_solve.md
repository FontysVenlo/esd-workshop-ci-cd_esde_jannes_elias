# Which Problem Does CI/CD Solve?

*The following content is fully based on* (Viet Hoang Nghiem, 2025).

In many organizations, software development is still slow, manual, and error-prone.  
Between an idea and its release to customers, weeks or even months can pass.  
During this time, competitors may deliver faster, bugs remain undiscovered, and teams lose valuable time on manual processes and coordination.

## The Problems Without CI/CD

1. **Manual Testing**  
   Developers often test their changes only on their local machines with different setups and tools.  
   This makes results inconsistent — something that works locally may fail in production.  
   Automatic and repeatable tests are usually missing.  
   —> The well-known phrase *“Works on my machine”* becomes a real problem.

2. **Manual Quality Assurance**  
   Test teams click through applications manually or follow checklists.  
   Feedback arrives late, tests are incomplete, and errors often go unnoticed until production.

3. **Manual Deployment**  
   Deploying code to production is often done by hand.  
   Configuration changes, service restarts, and file transfers happen manually — often at night or on weekends to avoid user disruption.  
   This causes stress, delays, and a high risk of mistakes.

### The Result

- Slow and unpredictable release cycles
- Frustrated development teams
- Hidden bugs that reach customers
- Wasted time on repetitive, manual work

## The Solution: CI/CD

**Continuous Integration (CI)** and **Continuous Delivery/Deployment (CD)** solve these problems by **automating the entire development and release process**.

With CI/CD:

- Code changes are automatically built and tested immediately after each commit.
- Errors are detected early — long before they reach production.
- Deployments happen automatically and safely, without manual steps.
- Teams work together more closely instead of in separate silos.

The result is **faster, safer, and more reliable releases** — and a smoother workflow for everyone involved.

## Comparison: Without CI/CD vs. With CI/CD

| **Process Step**              | **Without CI/CD**                                                    | **With CI/CD**                                                                                  |
|-------------------------------|----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| **New Feature Completed**     | Manual, often incomplete testing                                     | Automated testing runs immediately after each commit                                            |
| **Error Detection**           | Detected late —> during manual testing or even in production         | Detected early through automated checks and unit tests                                          |
| **Test Environment Setup**    | Manual, inconsistent, error-prone                                    | Automated and consistent across all environments                                                |
| **Go-Live / Deployment**      | Manual deployment, often done at night or weekends to avoid downtime | Automated, scheduled deployments with rollback options                                          |
| **Team Workload**             | High manual effort, stressful release days                           | Streamlined workflow, faster feedback, and fewer errors                                         |              

---

## Source

Viet Hoang Nghiem (2025): *CI/CD verstehen: Der Schlüssel zu schnelleren, sicheren Software-Releases* [online]
https://www.dataspark.de/blog/neues-von-dataspark-in-unserem-blog-1/ci-cd-verstehen-der-schlussel-zu-schnelleren-sicheren-software-releases-31
[Accessed: 08.11.2025]