# Team Rules

In order to keep things consistent over the development of the project these are the rules every member should follow:

- For every feature/US a new branch must be created.
- For almost every feature/US a Pull Request must be made & reviewed.
- In the end of each sprint create a new release on github named `sprint-X`.
- Foreach task an issue must be created.

> 💡 Note: Some actions don't require a branch, like updating the README.md file.

## Conventions

### Commits

purpose(specification[optional]): descriptive message (#issue-number[optional])

> 💡 Note: You may want to link the issue number in the commit message. Example: `feat: added vaccine scheduling system (#123)`.

> 💡 Note: If the commit closes an issue you can use the keyword `closes` or `fixes` followed by the issue number. Example: `fix(meetings): fixed meeting creation bug (closes #15)`

#### Example

- feat: added vaccine scheduling system
- docs(readme): added new us07 specifications
- feat(ui): added new button to patient screen (#15)
- fix(meetings): fixed meeting creation bug (closes #15)

### Branches

purpose/objective

> 💡 Note: When naming branches use dash case.

branch-name ✔️
branchName ✖️

#### Example

- feat/scheduling-system
- fix/bug-123

### Issues

The issues can be named in a more informal way but it must be clear what the issue is about.

#### Example

- Add vaccine scheduling system
- Fix bug on meeting creation

### Pull Requests

The pull request must have a title and a description. The title must follow the same convention as the commits. The description must have a link to the issue that is being solved.

#### Example

- Title: Feature/added vaccine scheduling system
- Description: This PR adds the vaccine scheduling system. Closes #123

## Example of purposes

- feat: a new feature is being added
- fix: something broken is being fixed
- docs: documentation changes
- refactor: changes to the code organization
- test: new tests were added/changed
- chore: minor code changes that does not impact functionality (Ex. removing/adding blank lines)

## Pull Requests

To ensure code quality and that all team members are in sync every feature/US that is relevant must have a Pull Request that must be reviewed by the team. Only after the approval from the development team the branch can be merged to the main branch.

## Useful commands

`git add -p` - It helps to separate large code changes in small commits. A iterative menu is presented to select what code pieces you want to add to a given commit.

`git diff` - Shows the difference between the current state and the last commit

`git branch -b branch-name` - Creates a branch named branch-name

`git status` - Lists all changed files

`git branch -a` - Lists all branches in the repository

`git checkout branch-name` - Change from current branch to branch-name

`git diff branch1..branch2` - Compare two branches

## Definition of Done (DoD)

- The code is reviewed by at least one team member
- The code is merged to the dev branch
- The code is deployed to the staging environment
- The code is tested in the staging environment

## Daily Meeting

The daily meeting is a meeting that happens every day at the same time. The meeting should be short and the goal is to keep everyone in sync with the project. The meeting should be done in a stand-up format, where each member answers the following questions:

- What did I do yesterday?
- What will I do today?
- What are the blockers?

## Sprint Planning

The sprint planning is a meeting that happens at the beginning of each sprint. The goal of this meeting is to define what will be done in the next sprint. The meeting should be done in a collaborative way, where each member can give their opinion about what should be done in the next sprint. The meeting should be done in the following steps:

- Review the previous sprint
- Define the sprint goal
- Define the sprint backlog
- Define the sprint tasks

## Sprint Review

The sprint review is a meeting that happens at the end of each sprint. The goal of this meeting is to review what was done in the sprint and to present the work to the stakeholders. The meeting should be done in the following steps:

- Review the sprint goal
- Review the sprint backlog
- Review the sprint tasks
- Present the work to the stakeholders

## Sprint Retrospective

The sprint retrospective is a meeting that happens at the end of each sprint. The goal of this meeting is to review what was done in the sprint and to identify what can be improved in the next sprint. The meeting should be done in the following steps:

- Review the sprint goal
- Review the sprint backlog
- Review the sprint tasks
- Identify what can be improved in the next sprint
