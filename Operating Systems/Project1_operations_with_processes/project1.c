/*
	O.S. Project 1
	Thomas Kistler
	3/9/18
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/time.h>

//function counts number of 3-digit integers in a file
int fileSize(char * filename)
{
	FILE * f;
	f = fopen(filename, "r");
	int count = 0, next = 0;
	while(fscanf(f,"%3d",&next) == 1)
	{
		count++;
	}
	return count;
}

int main(int argc, char *argv[])
{
   struct timeval start, stop;
   long elapsed_seconds;  /* diff between seconds counter */ 
   long elapsed_useconds; /* diff between microseconds counter */ 

   /*---------------FILE NAME-----------------------*/

   char  * fileName = "./small.txt";

   /*-----------------------------------------------------*/

   FILE * fp;

   int numChildren = 0;
   int i, length;
   int fpos=0, count=0, sum=0;
   pid_t pid;
   int TOTAL = 0;
   int totalLength = 1000;
   //input
   printf("How many children (1, 2, or 4):\n");
   scanf("%d", &numChildren);

   printf("\nFile size: %d\n",fileSize(fileName));
   int fd[2*numChildren][2]; //parent+child pipe
   // create all pipes
   for (i=0; i < (numChildren * 2); i++)
   {
       pipe(fd[i]);
   }
  
   //start timer
   gettimeofday(&start, NULL); 

   for (i=0; i<numChildren; i++)
   {
       if((pid = fork()) == 0) // child process
       {
           pid = getpid();

           // read from parent
           length = read(fd[i][0], &fpos, sizeof(fpos));
           if (length > 0)
           {
               fp = fopen(fileName, "r");
               fseek (fp, fpos, SEEK_SET);
               count = 0;
               sum = 0;

               printf("Child %d read position: %d from parent\n", pid, fpos);

               int nextInt = 0;
               for(int j = 0; j < (totalLength/numChildren); j++)
               {
                       fscanf(fp, "%3d", &nextInt);
                       sum += nextInt;
                       count++;
               }

               //write to parent
               write(fd[i+numChildren][1], &sum, sizeof(sum));
               printf("Child %d Sent %d to parent.\n", pid, sum);
           }else{
               printf("length of read is <= 0\n");
           }
       }

       // parent process
       pid = getpid();

       fpos = ((totalLength*3)/numChildren)*i; // offset of 3

       // write to child process
       write(fd[i][1], &fpos, sizeof(fpos));

       // wait for child responce
       length = read(fd[i+numChildren][0], &sum, sizeof(sum));
       if (length > 0)
       {
           printf("Parent Recieved %d from child.\n", sum);
           TOTAL += sum;
       }else{
           printf("Parent, Error with length\n");
       }
   }//endfor
   
   fclose(fp);
   //end timer
   gettimeofday(&stop, NULL); 
   printf("For %d child(ren):\n", numChildren);
   printf("Sum = %d\n\n",TOTAL);

   elapsed_seconds = stop.tv_sec - start.tv_sec; 
   elapsed_useconds = stop.tv_usec - start.tv_usec; 

   printf("\n");
   printf("Elapsed time = %ld seconds     +      %ld microseconds\n", elapsed_seconds, elapsed_useconds); 

   return 0;
}