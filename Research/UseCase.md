# When Is CI/CD Useful and When Is It Not?
*The following content is fully based on* (Bravin Wasike, 2024).

## ✅ When CI/CD is useful

*CI/CD can help any project, even very small ones, because it improves the development process in many ways.*

### 1. Reduced Manual Work
Manual deployments can be slow and easy to mess up.  
CI/CD automates tasks like:
- building
- testing
- deploying

This saves time and avoids many simple errors.

### 2. Early Bug Detection
CI/CD can run automated tests after every code change.  
This helps:
- catch bugs early
- avoid big problems later
- keep the codebase stable

### 3. Consistent Code Quality
CI/CD can check code automatically using:
- linters
- static analysis tools
- other quality checks

This helps keep the code clean and consistent.

### 4. Improved Collaboration
CI/CD gives teams:
- fast feedback
- shared visibility of problems
- automatic notifications

This helps team members work together more smoothly.

### 5. Scalability and Future Growth
Small projects usually grow over time.  
Having CI/CD from the beginning:
- creates a strong foundation
- makes future features easier to add
- prevents problems as the project becomes larger

### 6. Safer Deployments
Automated deployments make releases:
- more reliable
- less risky
- easier to repeat

This reduces downtime and mistakes.

## ❌ When CI/CD Is Not Useful

The original article strongly encourages using CI/CD, even for small projects.  
It does not list situations where CI/CD should not be used, but some cases can be understood from the context.

### 1. There Are No Automated Build or Test Steps
If a project has:
- no build step
- no automated tests
- no deployment process

…then CI/CD does not bring many benefits.

### 2. The Project Never Changes
CI/CD helps most when a project:
- changes often
- gets updates
- needs regular integration

A project that never changes does not need CI/CD.

### 3. No Version Control System Is Used
CI/CD depends on version control tools like Git.  
Without version control, a pipeline cannot work.

### 4. There Is Nothing Meaningful to Automate
CI/CD is helpful when you have:
- builds
- tests
- deployments

If a project has none of these, automation adds little value.

---

## Source

Based on:  
Bravin Wasike (2024) – *Why Every Project Needs a CI/CD Pipeline No Matter How Small* [online]
https://www.red-gate.com/simple-talk/devops/ci-cd/why-every-project-needs-a-ci-cd-pipeline-no-matter-how-small/#:~:text=Continuous%20Integration%20(CI)%20Continuous%20Integration%20is%20the,automated%20testing%20as%20part%20of%20the%20processes.
[Accessed: 16.11.2025]
