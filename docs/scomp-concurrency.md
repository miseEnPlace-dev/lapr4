# SCOMP - Java Concurrency

## Introduction

This document describes the SCOMP part of the project related to Java concurrency.

## The Problem

In our application there are two main concurrency problems:

- The Shared Board Server must be able to handle multiple clients at the same time.
- The post it state must be able to be updated by multiple users at the same time.

To solve this issue we can use Java locking mechanisms and threads.

## The solution

### Java Locking

JPA has already a mechanism of locking, known as the `@Version` annotation. This annotation enables the usage of `optimistic locking`. When an entity is updated, the version number is incremented. If the version number of the entity in the database differs from the version number of the entity in memory during an update, an exception, `OptimisticLockException`, is thrown. This mechanism verifies that the object has not been altered by another user between the read and write operations. The `PostIt` class includes the `@Version` annotation, ensuring that the version number increments during updates and that an exception is thrown if the database and in-memory versions differ.

> [Click here](/ecourse.core/src/main/java/eapli/ecourse/postitmanagement/domain/PostIt.java) to see the full code.

Despite this mechanism, it is also possible to implement a `pessimistic locking` mechanism. This approach involves locking the entity in the database when it is read, so that no other user can read or write the entity until the lock is released. This is typically implemented through using database row locks. The good side of this approach is that is guaranteed that no other user can read or write the entity until the lock is released.

Example:

```java
public PostIt readPostIt(Long id) {
  EntityManager em = PersistenceContext.repositories().entityManager();
  EntityTransaction tx = em.getTransaction();
  tx.begin();
  try {
    PostIt postIt = em.find(PostIt.class, id, LockModeType.PESSIMISTIC_WRITE);
    tx.commit();
    return postIt;
  } catch (Exception e) {
    tx.rollback();
    throw e;
  }
}
```

However, we decided not to implement this mechanism because this is generally not desirable when developing web applications. This approach consumes significant database resources and can lead to deadlocks.

### Threads

For the Shared Board, a TCP server was created that listens for connections from clients. When a client is accepted, a new thread is created to handle that client. This thread is responsible for reading the client's requests and sending the appropriate responses.

The TCP server is implemented in the `TcpServer` class. The `TcpServer` class has a `start()` method that creates a `ServerSocket` and listens for connections. The `ClientHandler` class implements the `Runnable` interface. When the `TcpServer` accepts a connection from a client, it creates a new `Thread` instance that receives the `ClientHandler` instance in its constructor and calls the `start()` method of the thread. This method returns immediately (the thread continues to run) and the server can thus wait for more client connections.

> [Click here](/ecourse.common.board/src/main/java/eapli/ecourse/common/board/TcpServer.java) to see the full code.

### Local Objects

To save the user session on each client's thread, each thread creates an instance of the class `ClientState` using the `ThreadLocal` class. Threads are multiple "contexts" executing in the same memory space. Each Thread has its own stack, but the heap is shared between all threads. As all objects are stored in the heap (which is shared), we needed to use `ThreadLocal` to create a `ClientState` instance so that each thread has its own instance of this class.

> [Click here](/ecourse.daemon.board/src/main/java/eapli/ecourse/daemon/board/clientstate/ClientState.java) to see the full code.

### Race Conditions

We implemented an online client counter to count the number of clients connected to the Shared Board Server. To prevent race conditions (two different threads trying to increment/decrement the same counter at the same time), synchronization between threads is required. The class `SafeOnlineCounter` implements the `increment()`, `decrement()` & `getCount()` methods. These 3 classes use the keyword `synchronized`, which basically means that when the method is executed, Java uses an internal lock specific to that object. Each thread, when calling the method, tries to obtain the lock; if another thread has already obtained the lock, the thread waits for this lock to be released. This lock can only be obtained by one thread at a time. The lock is obtained before the method runs and released after the method is executed.

> [Click here](/ecourse.common.board/src/main/java/eapli/ecourse/common/board/SafeOnlineCounter.java) to see the full code.

### Thread Signaling

It was implemented an online client count checker which works by implementing another thread alongside the TCP server. This thread is responsible for printing a message in the server's console if the number of clients connected to the server is a multiple of 3. The `notifyAll()` method is called whenever this condition is true. The `wait()` method causes the thread to wait until another thread calls the `notifyAll()` method on the same instance. When this happens, the thread is awakened and continues to execute. To guard against spontaneous wakeups, the `wait()` method is called inside a loop that checks if the condition is true. If the condition is not true, the thread goes back to sleep.

> [Click here](/ecourse.common.board/src/main/java/eapli/ecourse/common/board/OnlineSafeShared.java) to see the full code.

We also implemented an board update count checker which works by implementing another tread alongside the TCP server. This thread is responsible for printing every type of update that is made to the board by thread. It is also responsible to print the statistics made by thread each time a user exits the `Shared Board App`. Similar to the previous thread, this thread will also use the `notifyAll()` whenever a update was made and the `wait()` method will print message received by the server and then will go back to sleep again. To guard against spontaneous wakeups, the `wait()` method is called inside a loop that checks if the condition is true. If the condition is not true, the thread goes back to sleep.

For each client/thread, we keep track of the following statistics:

- Number of post it creations
- Number of post it updates
- Number of post it deletes
- Number of boards archived/unarchived

To achieve this, we created a Map that stores, for each new Thread, the number of updates and the type of the update made in the server. Whenever the thread is awaken it will print the statistics of the change made by the thread and then goes back to sleep, waiting for another update. Whenever a user exits the `Shared Board App` the thread will print all the statistics of the updates made by the thread, as well as the global statistics of the server.

> [Click here](/ecourse.common.board/src/main/java/eapli/ecourse/common/board/BoardUpdatesShared.java) to see the full code.

### Notification

It was implemented a new Thread `MessageListener` that has a queue of messages. This thread has a method `receive()` that receives the expected codes and returns the first occurrence of a message in the queue with the expected code. If there are no occurrences of the expected codes in the queue, the method blocks until a message with one of the expected codes is received. When checking the queue, the messages that do not correspond to the expected codes are discarded.

When reading from a socket, it works like a FIFO queue (because we are using TCP!): the first message sent by the server will be the first to be received by the client. We can do the same with a thread that reads from the socket and puts the messages in a queue. Then, we can read from the queue instead of reading from the socket directly. This way, we can have a thread that is waiting for specific messages without blocking the entire socket or using two sockets, enabling us to implement, for example, notifications about changes in a board.

> [Click here](/ecourse.app.board.console/src/main/java/eapli/ecourse/app/board/lib/MessageListener.java) to see the full code.

### Improvements

We could expand on the notification service to allow the client to subscribe to specific notifications. This would be a better solution than the one we implemented because it would allow the client to receive only the notifications that it is interested in.

We could implement web sockets in the Shared Web Board App to enable real-time notifications about changes in the board. This would be a better solution than the one we implemented because it would not require the client to constantly send requests to the server to check if there are any changes in the board.

We could implement a thread pool in the Shared Board Server to limit the number of threads that can be created. This would be a better solution than the one we implemented because it would prevent the server from crashing if too many clients connect to the server at the same time.

## References

[TP 11 - Java Concurrency](https://moodle.isep.ipp.pt/pluginfile.php/280091/mod_resource/content/3/Java%20Concurrency.pdf)
[TP 3_4 - Concurrent-access](https://moodle.isep.ipp.pt/pluginfile.php/273742/mod_resource/content/0/EAPLI_ORM_TP_3_4_concurrent-access.pdf)
