//wait3.c
#include <stdio.h>        //need for printf
#include <unistd.h>       //need for getpid, getppid  

int main()
{

   pid_t pid, ppid, fork_return;
   int  generation, max_generation;
   long i;
   max_generation = 4;
   printf("generation             fork_return        pid            ppid\n");

   for (generation = 1; generation < max_generation; generation++){
      fork_return = fork();
      if ( fork_return == 0 ){
         for (i = 0; i < 100000000; i++);
         printf("%5d        child    %10d    %10d     %10d\n", generation, fork_return, getpid(), getppid());
      }
      else{
         wait();
         printf("%5d        parent   %10d    %10d     %10d\n", generation, fork_return, getpid(), getppid());
      }
   }
}
