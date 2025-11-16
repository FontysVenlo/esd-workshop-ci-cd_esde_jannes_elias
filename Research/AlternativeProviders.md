## GitHub Actions

| Pros                                                                 | Cons                                                                    |
|----------------------------------------------------------------------|-------------------------------------------------------------------------|
| Built into GitHub; seamless with repos, PRs, checks, releases        | Locked into GitHub ecosystem                                            |
| Huge marketplace of reusable actions                                 | Usage limits on GitHub-hosted runners (minutes/concurrency)             |
| Easy to start; great DX for OSS and small/medium teams               | Large orgs can end up with many scattered YAML workflows                |
| Supports GitHub-hosted & self-hosted runners                         | Complex pipelines can get hard to debug/structure without conventions   |
| Native OIDC integration for cloud providers (no long-lived secrets)  | Some advanced features still maturing vs older enterprise CI solutions  |


## GitLab CI/CD

| Pros                                                                  | Cons                                                        |
|-----------------------------------------------------------------------|-------------------------------------------------------------|
| Fully integrated DevOps platform (code, CI/CD, registry, security)    | Best experience assumes your code is on GitLab              |
| Powerful pipeline features (includes, child pipelines, environments)  | Self-managed GitLab requires significant ops & maintenance  |
| Good for monorepos and complex org-wide workflows                     | Can feel heavier/more complex for simple projects           |
| Easy to mix SaaS and self-managed runners                             | Migration from other platforms can be non-trivial           |
| Strong built-in security & compliance tooling                         | UI/UX can be overwhelming for new users                     |

## Jenkins

| Pros                                                          | Cons                                                                   |
|---------------------------------------------------------------|------------------------------------------------------------------------|
| Extremely flexible; works with any VCS, tool, or environment  | You operate everything: install, scale, secure, back up                |
| Massive plugin ecosystem for integrations                     | Plugin sprawl, version conflicts, and maintenance overhead             |
| Mature and widely adopted in enterprises                      | UI/UX feels dated vs modern managed CI platforms                       |
| Full control over environment and execution                   | No native “batteries-included” cloud; must provision agents/runners    |
| Great for legacy, on-prem, or highly customized workflows     | Harder to standardize; pipelines can become inconsistent across teams  |


---

## Source

GitHub Actions

GitHub (n.d.) GitHub Actions. Available at: https://docs.github.com/en/actions
 (Accessed: 15 November 2025).

GitHub (n.d.) GitHub Actions: Billing and usage. Available at: https://docs.github.com/en/actions/concepts/billing-and-usage
 (Accessed: 15 November 2025).

Spacelift (n.d.) GitHub Actions vs Jenkins. Available at: https://spacelift.io/blog/github-actions-vs-jenkins
 (Accessed: 15 November 2025).

Mirrorfolio Blog (n.d.) Surveying the merits and drawbacks of GitHub Actions. Available at: https://blog.mirrorfolio.com/surveying-the-merits-and-drawbacks-of-github-actions
 (Accessed: 15 November 2025).

GitLab CI/CD

GitLab (n.d.) GitLab CI/CD. Available at: https://docs.gitlab.com/ci/
 (Accessed: 15 November 2025).

GitLab (n.d.) Pipelines. Available at: https://docs.gitlab.com/ci/pipelines/
 (Accessed: 15 November 2025).

Knapsack Pro (n.d.) GitLab CI. Available at: https://knapsackpro.com/ci_servers/gitlab-ci
 (Accessed: 15 November 2025).

Jenkins

Jenkins (n.d.) Jenkins 2.0. Available at: https://www.jenkins.io/2.0/
 (Accessed: 15 November 2025).

Jenkins (n.d.) Pipeline. Available at: https://www.jenkins.io/doc/book/pipeline/
 (Accessed: 15 November 2025).

DevOps Technical Notes and Manuals (n.d.) Why use Jenkins over the other tools? Jenkins pros and cons. Medium. Available at: https://medium.com/devops-technical-notes-and-manuals/why-use-jenkins-over-the-other-tools-jenkins-pros-and-cons-d2d4811ae81c
 (Accessed: 15 November 2025).
