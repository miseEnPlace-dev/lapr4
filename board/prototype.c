#include "utils/utils.h"

#define ROWS 2
#define COLUMNS 2

#define CONCURRENT_PROCESSES 10

// Struct to simulate a shared board
typedef struct
{
  int board[ROWS][COLUMNS];
} shared_board_t;

int main()
{
  // Create shared memory for shared board
  shared_board_t *board = (shared_board_t *)create_memory("/prototype_shm", sizeof(shared_board_t));

  // Create semaphore to guarantee mutual exclusion in access to shared board
  sem_t *mutex = (sem_t *)create_semaphore("prototype_sem", 1);

  // Create concurrent processes, simulating multiple users reading/writing to board
  int user = create_childs(CONCURRENT_PROCESSES);

  if (user)
  {
    // Simulate users' behaviour:
    //
  }
}
