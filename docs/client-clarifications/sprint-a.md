# Sprint 1 Clarifications

This document is a collection of clarifications for the first sprint. The doubts are synthesized for easier reading.

## [Question 1](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21834)

> Should the user short name be part of the full name?

"My interpretation of short name is like a given name."

## [Question 2](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21836)

> As for the exam, is the title written in the exam the same as its unique title to identify?

"The title should be unique. Maybe it could be used to identify the exam."

## [Question 3](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21837)

> UI in console?

"In general, what is expected is a Java Console Application"

## [Question 4](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21835)

> Course id is unique and generated or inputed? Course title and name are the same?

"Unique code inputted by the administrator, e.g., "JAVA-1, that is used to identify it.
It has a name (name and title may refer to the same property of courses, and I would say that there is no need for it to be unique)"

## [Question 7](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21913)

> A course can open if the limits are exceeded or not? Who can setup the limits? Are the limits mandatory?

"Managers should be able to specify the limits. In my opinion the system should notify the manager if a course is out of the enrolment limits (before this course is changed to "in progress"). But, in the end, it is up to the manager to follow/enforce or not, the limits."

## [Question 8](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21922)

> Can one student be enrolled in different courses?

"Yes"

## [Question 9](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21899)

> Does the system need to support other roles and question types?

"Regarding user roles, the solution should support the ones necessary to cover all the actual functional requirements.

Regarding question types, only the referenced in the specification are required."

## [Question 10](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21945)

> Can the owner of a board set custom permissions for each user?

"Yes"

## [Question 11](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21972)

> Tax payer number format verifications?

"For the project with should focus on supporting the Portuguese tax payer number. You may consider single person tax payer (no need to support companies)."

## [Question 14](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21986)

> Can the same question be used in different exams? Or are the questions made for a specific exam?

"There is no "database" of questions (no identity)"

## [Question 15](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21987)

> Can a user have multiple roles?

"No"

## [Question 16](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21988)

> Difference between meeting and class

"Classes -> Regular and pre-defined participants
Meetings -> There is a concept of "invite", can be created by any user"

## [Question 17](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21994)

> Can a meeting and class overlap?

"Regarding classes:
FRC09 - Schedule of Class A teacher schedule a class (always a recurring class, happens every week). System must check if the Teacher is available for the class period

Regarding Meetings:
FRM01 - Schedule a Meeting A user schedules a meeting. The system must check if all participants are available and send invitations to participants.

In the case of Meetings they should not be created if the participants are not available."

## [Question 18](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21997)

> Teacher acronym formatting rules? Should it be unique?

"I think a sequence of capitalized words should be used for the teacher acronym. Maybe the length could be a configuration setting of the application.

I think it would be wise to enforce that acronyms be unique."

## [Question 19](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22003)

> "In regards to the exam, what do you mean by feedback and grading? Is it necessary to save the answers of the users showing and comparing them with the correct answer? Furthermore is it necessary to save the answer of the question?"

"The idea is to have feedback and grading similarly to what is available for tests in the moodle platform. How you may achieve this is not up to me to decide."

## [Question 21](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21971)

> Course code verifications?

"Course code should be unique and there are not restrictions on the format of the code."

## [Question 22](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22041)

> Where should a notification regarding the update of a board post appear? In the user app, the shared board app or both?

"it should be in the Shared Board App."

## [Question 23](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22064)

> Can any user of the system invite any other user? For example, can a student invite another student who is in a different course, or can a manager can create a meeting with any group of teachers.

"When in the document specification the term "User" is used it usually means "any user" of the system. Therefore, any user of the system can schedule a meeting and be a participant in a meeting."

## [Question 24](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22080)

> When a user does not respond to a meeting invitation, should his status be changed to "not responded" or "rejected"?

"The status of someone that did not answer should be "no answer" or "unknown""

## [Question 25](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22101)

> What is a "Course" and what is a "Class"?

"A course is a small duration course, almost equivalent to a UC (and not a degree course). A class is a regular class, that is associated to a course."

## [Question 26](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22120)

> Should it be possible to have a column with no title and no identifier in a board?

"In order for user to post content into a cell they must identify the cell. Therefore, I think at least, it should be possible to identify the cell by the number of its column and the number of its row. If the cells have titles, these titles can be used to identify the cells. However, it should always be possible to identify a cell by the column number and row number."

## [Question 27](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22133)

> Is the name of each actor either the full name or the short name?

"Both. A short name is is like a given name, the short name someone chooses to be called."

## [Question 28](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22171)

> Can a user own more than one board?

"Yes"

## [Question 29](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22187)

> If a student misses a exam, his grade should be 0 or what?

"Should be N/A. You should not notify anyone about the absence of a student."

## [Question 30](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22188)

> All questions should accept a feedback?

"Yes"

## [Question 31](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22007)

> Can a teacher be in charge of more than one course?

"Yes"

## [Question 32](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22202)

> Can a cell have more than one post?

"No"

## [Question 33](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22209)

> Can an exam be scheduled for more than one day?

"An exam has a open date and a close date."

## [Question 34](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22213)

> Rules for teacher's acronyms?

"Should be unique. Formed by a sequence of capitalized letters. The maximum length of the acronym should be a configuration setting of the application. Follow this rule (setting) for similar problems."

## [Question 35](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22227)

> FRE06 says "The system displays the grades of a class". Did you mean course instead of class?

"Yes. In some contexts "class" can also be translated to "turma", or all the students in a "class"."

## [Question 36](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22228)

> Should students sign up for exams?

"No. Exams should be available to all students of the course."

## [Question 37](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22229)

> Should someone be able to see the logs of a board?

"If a user as read access to the board he can view the history. Logs are important for FRB06 and FRB07."

## [Question 38](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22255)

> What information should be stored in the logs?

"All relevant informations so that FRB06 and FRB07 can be implemented, i.e., all the information that is needed for a possible "restore operation" in the future."

## [Question 39](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22261)

> Deploy in this sprint? Rules for deployment?

"Algumas user stories deste sprint são relativamente vagas e dependem do vosso planeamento. Por exemplo, que aplicações (das previstas no capitulo 4 terão, de facto, já alguma "versão" para instalarem?).

De qualquer das formas, a ideia é preparem o "deployment" em função dos objetivos de cada sprint. Do ponto de vista do cliente, seria interessante terem já uma versão "executável" (embora sem funcionalidades) das app Admin, Teacher e Student (e respectiva base de dados).

Nota: Em concreto, e apenas para a UC de LAPR4, existem vários "níveis" de deployment, que estão identificados no critério de avaliação "Implantação/Deployment (10%)"."

## [Question 40](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22262)

> Can the user choose a limit date for weekly classes? Or only at the end of the semester?

"There is no mention to any of that. Not even to a date of end of the course. If you choose to add this information, you are dumb. The course ends when the manager closes it."

## [Question 41](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22278)

> Do courses have a final grade?

"No. You only need to list the grade of exams."

## [Question 42](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22281)

> Should we track the changes that a manager does [to a course]?

"That is a good practice, but there is no requirement for that. There are other roles and requirements that need that (for instance, users and boards or users and meetings)."

## [Question 43](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22286)

> After an exame being created, can it be edited?

"No. FRE01 could be very complex, with many steps prone to user errors. As a final step, you should consider a "publish" operation that marks the end of the creation process."

## [Question 44](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22324)

> Is there any difference between a teacher and a teacher in charge? Do they have the same permissions regarding classes?

"No, there is no difference. In the app, they have the same permissions. You only need to register the teacher in charge for each course."

---

_Last question: "Teacher in charge and normal class teacher responsibilities", by Joao Leitao._

_Last updated at 13/04/2023 14:44_

