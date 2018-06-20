#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void main()
{
   int i, value, offset, current, size = 20;
   long sum;
   char *pstr;
   char buffer[size];
   FILE *fp;
   
   fp = fopen( "./tiny.txt", "r" );

   offset = size;

   current = 0;

   for (i = 0; i < 2; i++ ){

      printf("Pass: %4d\tCurrent: %10d\n", i, current);

      fseek( fp, current, SEEK_SET );           // always seeks from start of file

//      fseek( fp, offset, current );           // probably move efficient but as written
                                                // requires that first seek be done before loop

                                                // fseek just positions the file pointer

      fread( buffer, sizeof(buffer), 1, fp );   // fread reads the number of bytes in the 
                                                // buffer.  Varying the value of size in 
                                                // increments of 4 will change the number of
                                                // integers read by each fread.

      printf("Contents of Buffer:\n%s\n", buffer);

      current += offset;
   }
}
