<p>
<a href="https://classroom.github.com/online_ide?assignment_repo_id=10490917&assignment_repo_type=AssignmentRepo"><img src="https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg"></a>
<a href="https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-22-23-19/actions/workflows/maven.yml"><img src="https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-22-23-19/actions/workflows/maven.yml/badge.svg"></a>
</p>

# Project eCourse

```
        ,-----.
 ,---. '  .--./ ,---. ,--.,--.,--.--. ,---.  ,---.
| .-. :|  |    | .-. ||  ||  ||  .--'(  .-' | .-. :
\   --.'  '--'\' '-' ''  ''  '|  |   .-'  `)\   --.
 `----' `-----' `---'  `----' `--'   `----'  `----'
```

## 1. Description of the Project

In the 4th semester of the Informatics Engineering Degree at Instituto Superior de Engenharia do Porto (ISEP), a teaching process was adopted based on the development of a single project (Integrative Project) that enhances the integration and application of knowledge and skills from all the courses taught in this semester: Engineering of Applications (EAPLI), Languages and Programming (LPROG), Computer Networks (RCOMP), Computer Systems (SCOMP) & Laboratory and Project IV (LAPR4).

The project consists on the design and development of a platform for remote learning, called eCourse. This platform supports the activities of the major actors of a course, mainly teachers, students and managers.

### Main Functionalities

The main functionalities of the platform are:

- **Users**: The platform allows the creation of users with different roles (student, teacher & manager).
- **Course Management**: The platform allows, among several functionalities, the creation of courses, enrolments of students in courses, course teachers, bulk enrolment of students, respond to enlistments, schedule of classes, etc.
- **Exams**: Among several other functionalities, the platform allows the creation of exams, listing of grades, etc.
- **Shared Boards**: The platform has the possibility to create boards, share them, create post-its, view history, etc.
- **Meetings**: The platform allows the creation of meetings, the scheduling of meetings, invitations, etc.

The system is developed in Java and it's based on the Spring Framework. GitHub is used to manage the source code and for CI (continuous integration). Exams use a custom language made with the ANTLR tool. The solution can be deployed using several network nodes (database & the shared board server). The system was developed in a distributed way, using the Scrum methodology.

## 2. Planning and Technical Documentation

[Planning and Technical Documentation](docs/README.md)

## 3. How to Build

To build the project execute the script:

- On Linux/MacOS: `./build.sh`
- On Windows: `build.bat`

## 4. How to Execute Tests

To execute the tests execute the following command:

`mvn test`

To view the coverage report execute the script:

- On Linux/MacOS: `./coverage-report.sh`
- On Windows: `coverage-report.bat`

## 5. How to Run

To run the project execute the script:

- On Linux/MacOS: `./run.sh`
- On Windows: `run.bat`

## 6. How to Install/Deploy into Another Machine (or Virtual Machine)

_To Do_

## 7. How to Generate PlantUML Diagrams

To generate PlantUML diagrams for documentation execute the script (for the moment, only for Linux/Unix/MacOS):

`./generate-plantuml-diagrams.sh`
