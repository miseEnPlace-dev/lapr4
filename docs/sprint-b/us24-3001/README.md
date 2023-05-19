# User Story 3001 - Shared Boards' Infrastructure

> As Project Manager, I want the team to prepare the communication infrastruture for the Shared Boards and the deployment of the solution.

|             |                   |
| ----------- | ----------------- |
| ID          | 24                |
| Sprint      | B                 |
| Application | 4 - Shared Boards |
| Priority    | 1                 |

## Acceptance Criteria

- This functional part of the system has very specific technical requirements.
- It must follow a client-server architecture, where a client application is used to access the shared boards that should be implemented in a server.
- Communications between these two components must follow specific protocol described in a document from RCOMP ("Application Protocol").
- Also, the client application can not access the relational database, it can only access the server application.
- The solution should be deployed using several network nodes.
- It is expected that, at least, the relational database server and the shared board server be deployed in nodes different from localhost, preferably in the cloud.

## Requirements

- **NFR10** Shared Board Architecture - This functional part of the system has very specific technical requirements. It must follow a client-server architecture, where a client application is used to access the shared boards that should be implemented in a server. Communications between these two components must follow specific protocol described in a document from RCOMP ("Application Protocol"). Also, the client application can not access the relational database, it can only access the server application. The client app should implement an HTTP server to be able to generate the "views" of the boards. This should be done automatically, without the intervention of the users (i.e., without reload of the web page) by using AJAX.
- **NFR14** Deployment - The solution should be deployed using several network nodes. It is expected that, at least, the relational database server and the shared board server be deployed in nodes different from localhost, preferably in the cloud.
