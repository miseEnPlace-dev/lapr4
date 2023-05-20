# User Story 3003 - Explore Synchronization Problems of Shared Boards

|             |                   |
| ----------- | ----------------- |
| ID          | 26                |
| Sprint      | B                 |
| Application | 4 - Shared Boards |
| Priority    | 1                 |

---

## 1. Context

This functional part of the system has very specific technical requirements, particularly some concerns about synchronization problems. In fact, several clients will try to concurrently update boards. As such, to explore and study this concurrency scenario a "model" of a solution must be implemented and evaluated exceptionally using the C programming language, and using processes and semaphores.

---

## 2. Conceptual Solution

### 2.1. Example of Synchronization Problems: Race Condition

When using a shared board, where multiple users can write/read information from it, there are several problems that can arise. One of them is when a user is reading information from the board, while simultaneously another user is writing information to the board. This can lead to the reader user reading information that is not updated, when the writing process is executed after the reading process.
The example above is one example of a "race condition" - a synchronization problem that can occur when multiple processes are trying to access the same resource concurrently.

### 2.2. Possible Solution: Semaphores

To solve this problem, and in order to synchronize the multiple accesses to the shared board, semaphores can be used. Semaphores are objects provided by the operating system that can be used to limit the simultaneous access to a shared resource and/or signal the occurrence of events. A semaphore holds an integer value, that can be incremented or decremented.
When a process wants, for example, to access a shared resource, it must first try to decrement the semaphore value. If the decrement is possible, i.e., if the decrement operation returns a positive value (or zero), then the process can access the shared resource. If the decrement operation cannot be performed (decrement the semaphore value would return a negative value), then the process must wait until the semaphore value is incremented by another process. When a process is waiting, the OS puts the process in the WAITING state, meaning that it's not using CPU resources. When the semaphore value is incremented, the OS puts the process in the READY state, continuing the execution of the process.´


### 2.3. Prototype Solution: Readers/Writers Problem

The Readers/Writers problem is a classic synchronization problem that can be used to simulate the access to a shared board. In this problem, there are multiple readers and writers processes that can access a shared resource. The readers can only read information from the board, while the writers can write information to the board. The readers can access the board simultaneously, but only one writer can access the board at a time. When a writer is accessing the board, no other reader or writer can access the board.

## 3. Pseudocode Solution

In order to simulate the access to a shared board, it was created a matrix of 10x10, where each position of the matrix represents a position of the board. Each position of the matrix can have a value of 0 or 1, where 0 represents an empty position and 1 represents a position with information. The matrix is initialized with all positions with value 0.

