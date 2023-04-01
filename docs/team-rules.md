# Team Rules

In order to keep things consistent over the development of the project these are the rules every member should follow:

- For every feature/US a new branch must be created.
- In the end of each sprint create a branch named snap/sprint-X.

> Note: Some actions don't require a branch, like updating the README.md file.

## Conventions

Branch names & commit messages:

### Commits

purpose(specification[optional]): descriptive message

#### Example

- feat: added vaccine scheduling system
- docs(readme): added new us07 specifications
- feat(ui): added new button to patient screen

### Branches

purpose/objective

> Note: When naming branches use dash case.

branch-name ✔️
branchName ✖️

#### Example

- feat/scheduling-system
- fix/bug-123

## Example of purposes

- feat: a new feature is being added
- fix: something broken is being fixed
- docs: documentation changes
- refactor: changes to the code organization
- test: new tests were added/changed
- chore: minor code changes that does not impact functionality (Ex. removing/adding blank lines)

## Useful commands

`git add -p` - It helps to separate large code changes in small commits. A iterative menu is presented to select what code pieces you want to add to a given commit.

`git diff` - Shows the difference between the current state and the last commit

`git branch -b branch-name` - Creates a branch named branch-name

`git status` - Lists all changed files

`git branch -a` - Lists all branches in the repository

`git checkout branch-name` - Change from current branch to branch-name

`git diff branch1..branch2` - Compare two branches
