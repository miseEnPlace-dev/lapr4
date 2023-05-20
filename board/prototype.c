#include "utils/utils.h"
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <wait.h>
#include <semaphore.h>

// Number of Rows and Columns of shared board
#define ROWS 2
#define COLUMNS 2

// Number of concurrent users (number of generated children)
#define CONCURRENT_USERS 10

// Struct to simulate a shared board
typedef struct
{
  int board[ROWS][COLUMNS];
} shared_board_t;

int main()
{
  // Create shared memory for shared board
  shared_board_t *shared_board = (shared_board_t *)create_memory("/prototype_shm_board", sizeof(shared_board_t));
  // Create shared memory for counter of current board readers
  int *readers = (int *)create_memory("/prototype_shm_readers", sizeof(int));

  // Create semaphore to guarantee mutual exclusion in access to counter of readers
  sem_t *mutex_readers = (sem_t *)create_semaphore("prototype_sem_mutex_readers", 1);
  // Create semaphore to guarantee priority to writers
  sem_t *priority = (sem_t *)create_semaphore("prototype_sem_priority", 1);
  // Create semaphore to guarantee mutual exclusion in access to board
  sem_t *mutex_board = (sem_t *)create_semaphore("prototype_sem_mutex_board", 1);

  // Create concurrent processes, simulating multiple users reading/writing to board
  int user = create_childs(CONCURRENT_USERS);

  // If child (create_childs returns 0 to Parent and 1... to Children)
  if (user)
  {
    // Simulate users' behaviour:
    // Odd users will try to read from board
    // Even users will try to write to board

    // Check if user ID is odd or even
    if (user & 1)
    {
      // Even user = Writer

      // Decrement/Wait priority's sem to guarantee my priority to board access
      sem_wait(priority);
      // Decrement/Wait board mutex's sem to guarantee my unique access to board
      sem_wait(mutex_board);

      // Write number to cell [row][column]
      int number = generate_random(1, 50);
      int row = user & ROWS;
      int column = user & COLUMNS;
      shared_board->board[row][column] = number;
      printf("[U%d] wrote the number %d in cell [%d][%d]\n", user, number, row + 1, column + 1);

      // Increment priority's sem
      sem_post(priority);
      // Increment board mutex's sem
      sem_post(mutex_board);

      exit(0);
    }
    else
    {
      // Odd user = Reader

      // Check if there is no writer writing at the moment. If there is any, wait for him to finish
      sem_wait(priority);
      sem_post(priority);

      // Increment number of readers
      // Decrement/Wait to guarantee exclusive access to shm
      sem_wait(mutex_readers);
      *readers += 1;
      // If I'm the first reader starting the reading process, guarantee unique access to board
      // If there is already a reader reading, there is no need to decrement sem
      if (*readers == 1)
        sem_wait(mutex_board);
      // Increment shm sem
      sem_post(mutex_readers);

      // Read number from cell [row][column]
      int row = user & ROWS;
      int column = user & COLUMNS;
      int number = shared_board->board[row][column];
      printf("[U%d] read the number %d from cell [%d][%d]\n", user, number, row + 1, column + 1);

      // Decrement number of readers
      // Decrement/Wait to guarantee exclusive access to shm
      sem_wait(mutex_readers);
      *readers -= 1;
      // If I'm the last reader leaving the reading process, increment the mutex's sem, letting other proccess access the board
      // If there is still a reader reading, cannot increment sem already
      if (*readers == 0)
        sem_post(mutex_board);
      // Increment shm sem
      sem_post(mutex_readers);

      exit(0);
    }
  }

  // Wait for users to finish
  for (int i = 0; i < CONCURRENT_USERS; i++)
    if (wait(NULL) == -1)
      puts("wait failed");

  // Clean sems and shms
  clean_memory("/prototype_shm_board");
  clean_memory("/prototype_shm_readers");
  clean_semaphore("prototype_sem_mutex_readers");
  clean_semaphore("prototype_sem_priority");
  clean_semaphore("prototype_sem_mutex_board");

  puts("Terminating...");

  return 0;
}
