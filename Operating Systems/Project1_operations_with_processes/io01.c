#include <stdio.h>

void main()
{
   int i, j;
   FILE *fp;
   
   fp = fopen("./small.txt", "r");

   for (i = 0; i < 1000; i++){
      fscanf( fp, "%3d", &j );
      printf("%03d\n", j);
   }
}


