# SCOMP - Java Concurrency

## Introduction

This document describes the SCOMP part of the project related to Java concurrency.

## Threads

For the Shared Board, a TCP server was created that listens for connections from clients. When a client is accepted, a new thread is created to handle that client. This thread is responsible for reading the client's requests and sending the appropriate responses.

The TCP server is implemented in the `TcpServer` class. The `TcpServer` class has a `start()` method that creates a `ServerSocket` and listens for connections. The `ClientHandler` class implements the `Runnable` interface. When the `TcpServer` accepts a connection from a client, it creates a new `Thread` instance that receives the `ClientHandler` instance in its constructor and calls the `start()` method of the thread. This method returns immediately (the thread continues to run) and the server can thus wait for more client connections.

## Local Objects

To save the user session on each client's thread, each thread creates an instance of the class `ClientState` using the `ThreadLocal` class. Threads are multiple "contexts" executing in the same memory space. Each Thread has its own stack, but the heap is shared between all threads. As all objects are stored in the heap (which is shared), we needed to use `ThreadLocal` to create a `ClientState` instance so that each thread has its own instance of this class.

## Race Conditions

We implemented an online client counter to count the number of clients connected to the Shared Board Server. To prevent race conditions (two different threads trying to increment/decrement the same counter at the same time), synchronization between threads is required. The class `SafeOnlineCounter` implements the `increment()`, `decrement()` & `getCount()` methods. These 3 classes use the keyword `synchronized`, which basically means that when the method is executed, Java uses an internal lock specific to that object. Each thread, when calling the method, tries to obtain the lock; if another thread has already obtained the lock, the thread waits for this lock to be released. This lock can only be obtained by one thread at a time. The lock is obtained before the method runs and released after the method is executed.

## Thread Signaling

<!-- TODO -->

De 3 em 3 clientes print, ....

## References

[TP 11 - Java Concurrency](https://moodle.isep.ipp.pt/pluginfile.php/280091/mod_resource/content/3/Java%20Concurrency.pdf)
