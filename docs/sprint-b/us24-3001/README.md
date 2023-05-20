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

## Analysis

A document with specific technical requirements was provided by the RCOMP course. This document describes the communication protocol between the client and the server, with the purpose of facilitating data exchanges between the Shared Board App and the Shared Board Server network applications.

- A TCP connection is established between the client and the server;
- The client sends a request to the server, with the server accepting incoming requests;
- Every request has a mandatory response format described in the communication protocol document;
- Once the connection is established, it is kept alive and is used for all required data exchanges while the clieent application is running.

## Communication Protocol Message Format

The communication protocol message format is described in the following table:

| Field        | Offset (bytes) | Length (bytes) | Description                                                                                                                                                                                   |
| ------------ | -------------- | -------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `VERSION`    | 0              | 1              | SBP message format version. This field is a single byte and should be interpreted as an unsigned integer (0 to 255). The present message format version number is one.                        |
| `CODE`       | 1              | 1              | This field identifies the type of request or response, it should be interpreted as an unsigned integer (0 to 255).                                                                            |
| `D_LENGTH_1` | 2              | 1              | These **two** fields `D_LENGTH_1` & `D_LENGTH_2` are used to specify the length in bytes of the `DATA` field. Both these fields are to be interpreted as unsigned integer numbers (0 to 255). |
| `D_LENGTH_2` | 3              | 1              | The length of the `DATA` field is to be calculated as: `D_LENGTH_1 + 256 x D_LENGTH_2`. The length of the `DATA` field may be zero, meaning it does not exist.                                |
| `DATA`       | 4              | -              | Contains data to meet the specific needs of the participating applications, the content depends on the message code.                                                                          |

## Communication Protocol Message Codes

The communication protocol message codes are described in the following table:

| Code | Description | Meaning                                                                                                                                                                                                                                                                                                |
| ---- | ----------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| 0    | `COMMTEST`  | Communications test request with no other effect on the counterpart application than the response with a code two message (ACK). This request has no data.                                                                                                                                             |
| 1    | `DISCONN`   | End of session request. The counterpart application is supposed to respond with a code two message, afterwards both applications are expected to close the session (TCP connection). This request has no data.                                                                                         |
| 2    | `ACK`       | Generic acknowledgment and success response message. Used in response to successful requests. This response contains no data.                                                                                                                                                                          |
| 3    | `ERR`       | Error response message. Used in response to unsuccessful requests that caused an error. This response message may carry a human readable phrase explaining the error. If used, the phrase explaining is carried in the `DATA` field as string of ASCII codes, it’s not required to be null terminated. |
| 4    | `AUTH`      | User authentication request. Described ahead.                                                                                                                                                                                                                                                          |

## Authentication Request

Once the TCP connection between the Shared Board App application (client) and the Shared Board Server application (server) is established, the Shared Board App is forced to authenticate the local user by sending an `AUTH` request.

- Prior to successful `AUTH`, the server must ignore any request from the client with a code value above four and send back an `ERR` message as response.
- The user authentication is achieved by a username and password pair, both will be provided to the client application (Shared Board App) by the local user running it.
- The username and the password values are incorporated in the `AUTH` request at the `DATA` field as two null terminated strings of ASCII codes, first the username, followed by the password.
- The response to an `AUTH` request may be an `ACK`, meaning the authentication was successful, or an `ERR`, meaning it has failed. In the latter case, additional `AUTH` requests could be tried by the client.
