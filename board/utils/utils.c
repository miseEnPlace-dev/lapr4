/**
 * Utils made by Group 02, to make SCOMP classes a bit easier.
 * All methods were either fully made by us or adapted from TP's slides.
 */

#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <semaphore.h>

#include "utils.h"

/**
 * Prints an array of integers.
 */
void print_array(int *arr, int size)
{
  for (int i = 0; i < size; i++)
    printf("[%i]", arr[i]);
  puts("");
}

/**
 * Prints a matrix of integers.
 */
void print_matrix(int **arr, int rows, int columns)
{
  for (int i = 0; i < rows; i++)
    print_array(arr[i], columns);
}

/**
 * Initializes an array of integers with random values between min and max.
 */
void initialize_array(int *arr, int size, int min, int max)
{
  srand(time(NULL) + getpid());
  for (int i = 0; i < size; i++)
    arr[i] = rand() % (max - min + 1) + min;
}

/**
 * Returns a random number between min and max.
 */
int generate_random(int min, int max)
{
  srand(time(NULL) + getpid());
  return rand() % (max - min + 1) + min;
}

/**
 * Creates n childs and returns the child's id to them, 0 to the parent.
 */
int create_childs(int n)
{
  for (int i = 0; i < n; i++)
  {
    pid_t p = fork();
    if (p == -1)
    {
      perror("fork failed");
      exit(1);
    }

    if (p == 0)
      return i + 1;
  }
  return 0;
}

/**
 * Creates a pipe and initializes the file descriptors.
 */
void create_pipe(int *fd)
{
  if (pipe(fd) == -1)
  {
    perror("pipe failed");
    exit(1);
  }
}

/**
 * Creates shared memory and returns the pointer to it.
 */
void *create_memory(char *name, int size)
{
  // creates a shared memory area
  int fd = shm_open(name, O_CREAT | O_EXCL | O_RDWR, S_IRUSR | S_IWUSR);

  if (fd < 0)
  {
    perror("shm_open failed");
    exit(1);
  }

  // define the size of the area and init to 0
  if (ftruncate(fd, size) < 0)
  {
    perror("ftruncate failed");
    clean_memory(name);
    exit(1);
  }

  // map the shared memory object in the process addr space
  return mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
}

/**
 * Links to an existing memory area, returning the pointer to it.
 * Same as create_memory, but shm_open does not receive O_CREAT flag.
 */
void *link_memory(char *name, int size)
{
  // links to a shared memory area
  int fd = shm_open(name, O_RDWR, S_IRUSR | S_IWUSR);

  if (fd < 0)
  {
    perror("shm_open failed");
    exit(1);
  }

  // define the size of the area and init to 0
  if (ftruncate(fd, size) < 0)
  {
    perror("ftruncate failed");
    clean_memory(name);
    exit(1);
  }

  // map the shared memory object in the process addr space
  return mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
}

/**
 * Cleans the shared memory.
 */
void clean_memory(char *name)
{
  shm_unlink(name);
}

/**
 * Creates a semaphore, initializes it and returns the pointer to it.
 */
sem_t *create_semaphore(char *name, int value)
{
  sem_t *sem = sem_open(name, O_CREAT | O_EXCL, S_IRWXU, value);

  if (sem == SEM_FAILED)
  {
    perror("sem_open failed");
    exit(1);
  }

  return sem;
}

/**
 * "Links" to an existing semaphore and returns the pointer to it.
 */
sem_t *link_semaphore(char *name)
{
  sem_t *sem = sem_open(name, 0, S_IRWXU);

  if (sem == SEM_FAILED)
  {
    perror("sem_open failed");
    exit(1);
  }

  return sem;
}

/**
 * Cleans the semaphore.
 */
void clean_semaphore(char *name)
{
  sem_unlink(name);
}

/**
 * Opens a new file, with given permissions, returning the pointer to it.
 */
FILE *open_file(char *path, char *options)
{
  FILE *file = fopen(path, options);

  if (file == NULL)
  {
    perror("fopen failed");
    exit(1);
  }

  return file;
}

/**
 * Reads a string with the given length from file.
 */
char *read_string_from_file(FILE *file, int length)
{
  char *buffer = calloc(1, length);

  if (buffer == NULL)
  {
    perror("calloc failed");
    exit(1);
  }

  fread(buffer, 1, length, file);

  return buffer;
}

/**
 * Closes the given file.
 */
void close_file(FILE *file)
{
  fclose(file);
}