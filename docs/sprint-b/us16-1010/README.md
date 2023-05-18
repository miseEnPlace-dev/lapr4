# User Story 1010 - Schedule a Class

|             |             |
| ----------- | ----------- |
| ID          | 16          |
| Sprint      | B           |
| Application | 2 - Courses |
| Priority    | 1           |

---

## 1. Context

This is the first time the task is assigned to be developed and is to be completed in this sprint. This user story is a feature.

## 2. Requirements

_In this section you should present the functionality that is being developed, how do you understand it, as well as possible correlations to other requirements (i.e., dependencies)._

_Example_

**US 1010** As Teacher, I want to schedule a class.

<!-- - G002.1. Blá Blá Blá ... -->

<!-- - G002.2. Blá Blá Blá ... -->

<!-- _Regarding this requirement we understand that it relates to..._ -->

## 1.3. Functional Requirements

> **FRC09** Schedule of Class - A teacher schedule a class (always a recurring class, happens every week). System must check if the Teacher is available for the class period.

## 1.4. Acceptance Criteria

> N/A.

## 3. Analysis

_In this section, the team should report the study/analysis/comparison that was done in order to take the best design decisions for the requirement. This section should also include supporting diagrams/artifacts (such as domain model; use case diagrams, etc.),_

## 4. Design

_In this sections, the team should present the solution design that was adopted to solve the requirement. This should include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (presenting the classes that support the functionality), the identification and rational behind the applied design patterns and the specification of the main tests used to validade the functionality._

### 4.1. Realization

### 4.2. Class Diagram

![a class diagram](class-diagram-01.svg "A Class Diagram")

### 4.3. Applied Patterns

### 4.4. Tests

**Test 1:** _Verifies that it is not possible to create an instance of the Example class with null values._

```
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed() {
	Example instance = new Example(null, null);
}
```

## 5. Implementation

_In this section the team should present, if necessary, some evidencies that the implementation is according to the design. It should also describe and explain other important artifacts necessary to fully understand the implementation like, for instance, configuration files._

_It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement._

## 6. Integration/Demonstration

_In this section the team should describe the efforts realized in order to integrate this functionality with the other parts/components of the system_

_It is also important to explain any scripts or instructions required to execute an demonstrate this functionality_

## 7. Observations

_This section should be used to include any content that does not fit any of the previous sections._

_The team should present here, for instance, a critical prespective on the developed work including the analysis of alternative solutioons or related works_

_The team should include in this section statements/references regarding third party works that were used in the development this work._
