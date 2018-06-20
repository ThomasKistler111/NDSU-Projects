// wait1.c
#include <stdio.h>        //need for printf
#include <unistd.h>       //need for getpid, getppid  

int main()
{

   pid_t pid, ppid, fork_return;
   int  generation;
   long i;

      generation = 1;
      printf("generation             fork_return        pid            ppid\n");

      fork_return = fork();
   
      if ( fork_return == 0 ){
         //make sure the child runs longer than the parent 
         for (i = 0; i < 10000000000; i++);
         printf("\n%5d        child    %10d    %10d     %10d\n", generation, fork_return, getpid(), getppid());
      }
      else{
         printf("\n%5d        parent   %10d    %10d     %10d\n", generation, fork_return, getpid(), getppid());
      }
}
