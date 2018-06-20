#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void main()
{
   int i, value;
   long sum;
   char *pstr;
   char buffer[2000];
   FILE *fp;
   
   fp = fopen( "./large.txt", "r" );

   fread( buffer, 78, 1, fp );

   printf("\nContents of Buffer:\n%s\n", buffer);


   printf("\nParsed values as long integers:\n");

   sum = 0;
 
   value = strtol( buffer, &pstr, 10 ); 
   sum += value;
   printf( "%03i\n", value );

   for (i = 1; i < 20; i++)
   {
      strcpy( buffer, pstr );
      value = strtol( buffer, &pstr, 10 ); 
      sum += value;
      printf( "%03i\n", value );
   }
   printf("\nSum of values = % i\n", sum );

}
