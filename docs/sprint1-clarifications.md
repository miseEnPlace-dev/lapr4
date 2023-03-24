# Sprint 1 Clarifications

This document is a collection of clarifications for the first sprint. The doubts are sintetized for easier reading.

## [Question 1](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21834)

> Should the user short name be part of the full name?

"My interpretation of short name is like a given name."

## [Question 2](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21836)

> As for the exam, is the title written in the exam the same as its unique title to identify?

"The title should be unique" ... "Maybe it could be used to identify the exam."

## [Question 3](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21837)

> UI in console?

In general, what is expected is a Java Console Application 

## [Question 4](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21835)

> Course id is unique and generated or inputed? Course title and name are the same?

"Unique code inputted by the administrator, e.g., "JAVA-1, that is used to identify it"
"It has a name (name and title may refer to the same property of courses, and I would say that there is no need for it to be unique)"

## [Question 7](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21913)

> A course can open if the limits are exceeded or not? Who can setup the limits? Are the limits mandatory?

"Managers should be able to specify the limits. In my opinion the system should notify the manager if a course is out of the enrolment limits (before this course is changed to "in progress"). But, in the end, it is up to the manager to follow/enforce or not, the limits."

## [Question 8](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21922)

> Can one student be enrolled in different courses?

Yes

## [Question 9](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21899)

> Does the system need to support other roles and question types?

Regarding user roles, the solution should support the ones necessary to cover all the actual functional requirements.

Regarding question types, only the referenced in the specification are required.

## [Question 10](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21945)

> Can the owner of a board set custom permissions for each user?

Yes

## [Question 11](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21972)

> Tax payer number format verifications?

For the project with should focus on supporting the Portuguese tax payer number. You may consider single person tax payer (no need to support companies).

## [Question 14](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21986)

> Can the same question be used in different exams? Or are the questions made for a specific exam?

There is no "database" of questions (no identity)

## [Question 15](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21987)

> Can a user have multiple roles?

No

## [Question 16](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21988)

> Difference between meeting and class

Classes -> Regular and pre-defined participants
Meetings -> There is a concept of "invite", can be created by any user

## [Question 17](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21994)

> Can a meeting and class overlap?

Regarding classes:
FRC09 - Schedule of Class A teacher schedule a class (always a recurring class, happens every week). System must check if the Teacher is available for the class period
 
Regarding Meetings:
FRM01 - Schedule a Meeting A user schedules a meeting. The system must check if all participants are available and send invitations to participants.
 
In the case of Meetings they should not be created if the participants are not available

## [Question 18](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21997)

> Teacher acronym formatting rules? Should it be unique?

"I think a sequence of capitalized words should be used for the teacher acronym. Maybe the length could be a configuration setting of the application.

I think it would be wise to enforce that acronyms be unique."

## [Question 19](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22003)

> "In regards to the exam, what do you mean by feedback and grading? Is it necessary to save the answers of the users showing and comparing them with the correct answer? Furthermore is it necessary to save the answer of the question?"

The idea is to have feedback and grading similarly to what is available for tests in the moodle platform. How you may achieve this is not up to me to decide.

## [Question 21](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21971)

> Course code verifications?

"Course code should be unique and there are not restrictions on the format of the code."

## [Question 22](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22041)

> Where should a notification regarding the update of a board post appear? In the user app, the shared board app or both?

"it should be in the Shared Board App."

## [Question 23](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22064)

> Can any user of the system invite any other user? For example, can a student invite another student who is in a different course, or can a manager can create a meeting with any group of teachers.

"When in the document specification the term "User" is used it usually means "any user" of the system. Therefore, any user of the system can schedule a meeting and be a participant in a meeting."

----

*Updated at: 24/03/2021 16:48*

