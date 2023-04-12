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

In the 4th semester of the Computer Engineering Degree in Instituto Superior de Engenharia do Porto (ISEP), a teaching process was adopted based on the development of a single project (Integrative Project) that enhances the integration and application of knowledge and skills from all the courses taught this semester: Application Engineering (EAPLI), Languages and Programming (LPROG), Computer Networks (RCOMP), Computer Systems (SCOMP) & Laboratory and Project IV (LAPR4).

The project consists of the design and development of a platform for remote learning, called eCourse. This platform will support the activities of the major actors of a course, mainly teachers, students and managers.

### Main Functionalities

The main functionalities of the platform are:

- **Users**: The platform will allow the creation of users with different roles (student, teacher & manager).
- **Course Management**: The platform will allow, among several functionalities, the creation of courses, enrollments of students in courses, course teachers, bulk enrollment of students, respond to enlistments, schedule of classes, etc.
- **Exams**: Among several other functionalities, the platform will allow the creation of exams, listing of grades, etc.
- **Shared Boards**: The platform will have the possibility to create boards, share them, create post-its, view history, etc.
- **Meetings**: The platform will allow the creation of meetings, the scheduling of meetings, invitations, etc.

The system will be developed in Java and will be based on the Spring Framework. GitHub will be used to source code management and CI (continuous integration). Exams will use a custom language made with the ANTLR tool. The solution will be deployed using several network nodes (database & the shared board server). The system will be developed in a distributed way, using the Scrum methodology.

## 2. Planning and Technical Documentation

[Planning and Technical Documentation](docs/README.md)

## 3. How to Build

_To Do_

## 4. How to Execute Tests

_To Do_

## 5. How to Run

_To Do_

## 6. How to Install/Deploy into Another Machine (or Virtual Machine)

_To Do_

## 7. How to Generate PlantUML Diagrams

To generate plantuml diagrams for documentation execute the script (for the moment, only for Linux/Unix/MacOS):

`./generate-plantuml-diagrams.sh`
