# How to Start building a CI/CD Pipeline

Getting started with CI/CD begins with understanding what a pipeline actually does: it automates the whole process from code to
deployment. Beginners should first learn the basics — what Continuous Integration is, what Continuous Delivery is, and what 
Continuous Deployment means. CI ensures that code is integrated and tested regularly, while CD makes sure the application is 
always in a releasable state (Khelifa Hana, 2022).

One of the first steps is choosing a platform that is reliable, scalable, and easy to use. Beginners benefit from tools that 
provide fast feedback and make debugging simple. The pipeline should give results within 5–10 minutes, helping developers stay 
productive (Khelifa Hana, 2022).

When setting up a CI/CD pipeline, developers should automate as much as possible. Beginners should aim to automate all stages 
of the development workflow, building, testing, deploying to staging, and finally production, to reduce manual work and 
improve reliability (H A R S H H A A, 2024).

It is also important to detect failures early. Beginners should design the pipeline so that tests run as soon as possible, 
for example unit tests right after the build. This way the pipeline fails early, and developers can fix issues before they 
affect later stages (H A R S H H A A, 2024).

Using version control correctly is essential. Beginners should create a separate branch for every feature, bug fix, or 
improvement, and merge it into the main branch only after all tests pass. This keeps the main branch always deployable (H A R S H H A A, 2024).

To avoid unnecessary complexity, it is best to start with a simple pipeline. Beginners should begin with a few clear 
stages and add more complexity over time. Large and complicated pipelines are harder to maintain, so it helps to split 
them into smaller, manageable jobs (H A R S H H A A, 2024).

Another key principle for beginners is to “listen to the pipeline.” A failing build should never be ignored. Every 
failure needs to be fixed immediately, or the faulty change should be reverted. This keeps the application in a working 
state at all times (Khelifa Hana, 2022).

A central recommendation is: build once, test many times. When building the application, all dependencies should be 
downloaded, the artifact created, and quality checks applied. All following tests — integration, end-to-end, and others 
should run on this same artifact for consistent results (Khelifa Hana, 2022).

Automated tests are essential. Beginners should learn to automate tests early and run them both locally and in the 
pipeline for every commit. A fast feedback loop saves time and prevents errors from accumulating (Khelifa Hana, 2022).

Speed is also very important. If the pipeline takes longer than 10 minutes, developers lose focus. Beginners should 
identify slow tests, optimize them, or run them in parallel wherever possible (Khelifa Hana, 2022).

Finally, beginners should understand that CI/CD is not only about tools, it is also a cultural mindset. “If you build it, 
you run it” means developers should take responsibility for the code even in production. Automated deployments, smoke tests, 
and rollback options help make the entire process stable and safe (Khelifa Hana, 2022).

---

## Source

Based on:  
Khelifa Hana (2022): *7 Best practices to set up your first CI/CD pipeline* [online]  
https://www.scaleway.com/en/blog/7-best-practices-to-set-up-your-first-ci-cd-pipeline/
[Accessed: 16.11.2025]

H A R S H H A A (2024) *Getting Started with CI/CD: A Beginner's Guide to Automating Your First Pipeline (with Jenkins)* [online]
https://dev.to/prodevopsguytech/getting-started-with-cicd-a-beginners-guide-to-automating-your-first-pipeline-483b
[Accessed: 16.11.2025]