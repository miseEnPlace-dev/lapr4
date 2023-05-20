#ifndef UTILS_H
#define UTILS_H

#include <semaphore.h>
#include <stdio.h>

/**
 * Prints an array of integers.
 */
void print_array(int *arr, int size);

/**
 * Prints a matrix of integers.
 */
void print_matrix(int **arr, int rows, int columns);

/**
 * Initializes an array of integers with random values between min and max.
 */
void initialize_array(int *arr, int size, int min, int max);

/**
 * Returns a random number between min and max.
 */
int generate_random(int min, int max);

/**
 * Creates n childs.
 * Returns 0 to parent, and child's index to each of them (starting in 1)
 */
int create_childs(int n);

/**
 * Creates a pipe and initializes the file descriptors.
 */
void create_pipe(int *fd);

/**
 * Creates shared memory and returns the pointer to it.
 */
void *create_memory(char *name, int size);

/**
 * Links to an existing memory area.
 */
void *link_memory(char *name, int size);

/**
 * Cleans the shared memory.
 */
void clean_memory(char *name);

/**
 * Creates a semaphore, initializes it and returns the pointer to it.
 */
sem_t *create_semaphore(char *name, int value);

/**
 * "Links" to an existing semaphore and returns the pointer to it.
 */
sem_t *link_semaphore(char *name);

/**
 * Cleans the semaphore.
 */
void clean_semaphore(char *name);

/**
 * Opens a new file, returning the pointer to it.
 */
FILE *open_file(char *path, char *options);

/**
 * Reads a string with the given length from file.
 */
char *read_string_from_file(FILE *file, int length);

/**
 * Closes the given file.
 */
void close_file(FILE *file);

#endif