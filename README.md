# Project eCourse ![CI](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-22-23-19/actions/workflows/maven.yml/badge.svg)

[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10490917&assignment_repo_type=AssignmentRepo)

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

The system is developed in Java and it's based on the EAPLI Framework provided by the course's teachers. GitHub is used to manage the source code and for CI (continuous integration). Exams use a custom language made with the ANTLR tool. The solution can be deployed using several network nodes (database & the shared board server). The system was developed in a distributed way, using the Scrum methodology.

## 2. Planning and Technical Documentation

[Planning and Technical Documentation](docs/README.md)

## 3. How to Build

Before building, make sure of the following:

- Ensure that Maven is installed and defined in the PATH environment variable.

- Check if you have a JDK installed. If not, you can download it from <https://www.oracle.com/java/technologies/javase-downloads.html>. The Java version used in the project is Java 1.8+, meaning that you can use any JDK version 1.8 or later. However, in order to generate the Javadoc and UML diagrams, the JDK version must be **strictly** 1.8. Confirm that the JAVA_HOME environment variable is set to the JDK installation directory.

- If you are using an Oracle database, you will need to change your Maven settings for downloading the Oracle drivers. Please see <https://blogs.oracle.com/dev2dev/entry/how_to_get_oracle_jdbc#settings> for more information.

To build the project, you first need to set all six properties files, located under all app's `src/main/resources` folders. Copy the `application.example.properties` file and rename it to `application.properties`. You can change the default values if you want, but it's not necessary.

> 💡 You can simply run `./properties.sh` (Linux/MacOS) to automatically create the properties files.

Then, execute the following script:

- On Linux/MacOS: `./rebuild-all.sh`
- On Windows: `rebuild-all.bat`

## 4. How to Execute Tests

To execute the tests, just execute the following command:

`mvn test`

## 5. How to Run

> ️️️️⚠️ Before running the project, you need to build the project. Follow the instructions described in section [3 - How to Build](#3-how-to-build).

First, start the H2 database. For that, **open a new terminal** and execute the following script:

- On Linux/MacOS: `./h2.sh`
- On Windows: `h2.bat`

Leave this terminal open and running. Go back to the terminal where you executed the build script and continue with the next steps.
In order to have the needed data for executing the app, you need to run the bootstrap script. For that, execute the following script:

- On Linux/MacOS: `./run-bootstrap.sh`
- On Windows: `run-bootstrap.bat`

You are now ready to run the app. Simply execute the script of the app you want to run:

- For the User app, execute the following script:
  - On Linux/MacOS: `./run-user.sh`
  - On Windows: `run-user.bat`
- For the Backoffice app, execute the following script:
  - On Linux/MacOS: `./run-backoffice.sh`
  - On Windows: `run-backoffice.bat`
- For the teacher app, execute the following script:
  - On Linux/MacOS: `./run-teacher.sh`
  - On Windows: `run-teacher.bat`
- For the manager app, execute the following script:
  - On Linux/MacOS: `./run-manager.sh`
  - On Windows: `run-manager.bat`

## 6. How to Install/Deploy into Another Machine (or Virtual Machine)

📝 _To Do_

## 7. How to Generate PlantUML Diagrams

To generate PlantUML diagrams for documentation execute the script (for the moment, available only for Linux/Unix/MacOS):

`./generate-plantuml-diagrams.sh`
