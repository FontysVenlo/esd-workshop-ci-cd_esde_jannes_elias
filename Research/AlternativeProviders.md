## GitHub Actions

| Pros                                                                | Cons                                                                   |
| ------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| Built into GitHub; seamless with repos, PRs, checks, releases       | Locked into GitHub ecosystem                                           |
| Huge marketplace of reusable actions                                | Usage limits on GitHub-hosted runners (minutes/concurrency)            |
| Easy to start; great DX for OSS and small/medium teams              | Large orgs can end up with many scattered YAML workflows               |
| Supports GitHub-hosted & self-hosted runners                        | Complex pipelines can get hard to debug/structure without conventions  |
| Native OIDC integration for cloud providers (no long-lived secrets) | Some advanced features still maturing vs older enterprise CI solutions |


## GitLab CI/CD

| Pros                                                                 | Cons                                                       |
| -------------------------------------------------------------------- | ---------------------------------------------------------- |
| Fully integrated DevOps platform (code, CI/CD, registry, security)   | Best experience assumes your code is on GitLab             |
| Powerful pipeline features (includes, child pipelines, environments) | Self-managed GitLab requires significant ops & maintenance |
| Good for monorepos and complex org-wide workflows                    | Can feel heavier/more complex for simple projects          |
| Easy to mix SaaS and self-managed runners                            | Migration from other platforms can be non-trivial          |
| Strong built-in security & compliance tooling                        | UI/UX can be overwhelming for new users                    |

## Jenkins

| Pros                                                         | Cons                                                                  |
| ------------------------------------------------------------ | --------------------------------------------------------------------- |
| Extremely flexible; works with any VCS, tool, or environment | You operate everything: install, scale, secure, back up               |
| Massive plugin ecosystem for integrations                    | Plugin sprawl, version conflicts, and maintenance overhead            |
| Mature and widely adopted in enterprises                     | UI/UX feels dated vs modern managed CI platforms                      |
| Full control over environment and execution                  | No native “batteries-included” cloud; must provision agents/runners   |
| Great for legacy, on-prem, or highly customized workflows    | Harder to standardize; pipelines can become inconsistent across teams |


---

## Source

Based on:  
Name (year) – *tittle*  
[Source](-)

GitHub Actions:

https://docs.github.com/en/actions
https://docs.github.com/en/actions/concepts/billing-and-usage
https://spacelift.io/blog/github-actions-vs-jenkins
https://blog.mirrorfolio.com/surveying-the-merits-and-drawbacks-of-github-actions

GitLab CI/CD

https://docs.gitlab.com/ci/
https://docs.gitlab.com/ci/pipelines/
https://knapsackpro.com/ci_servers/gitlab-ci

Jenkins

https://www.jenkins.io/2.0/
https://www.jenkins.io/doc/book/pipeline/
https://medium.com/devops-technical-notes-and-manuals/why-use-jenkins-over-the-other-tools-jenkins-pros-and-cons-d2d4811ae81c
