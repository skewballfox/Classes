#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "omp.h"

void SaveResult(char* str,int size,double time,double pi); //Just for debug

int main(int argc, char* argv[])
{
    long int total_number=strtol(argv[1],NULL,10);
    long int count = 0;
    double pi = 0;
	int thread_num=0;
	double start_time,end_time;
	int* Local_Count = NULL;
	double x=0,y=0;
    srand(time(NULL));
#pragma omp parallel
{
	if(omp_get_thread_num() == 0)
	{
		thread_num=omp_get_num_threads();
		printf("[%d] threads join the calculation\n",thread_num);
	}
	
}

	start_time=omp_get_wtime();
	
#pragma omp parallel 
{
	
	unsigned seed = time(NULL);
#pragma omp for private(x, y) reduction(+:count)
    for (long int i = 0; i < total_number; i++)
    {
        //Randomly generate point in (0,0)->(1,1)
        x = (double)rand_r(&seed) / RAND_MAX;
        y = (double)rand_r(&seed) / RAND_MAX;
        
        if ((x * x + y * y) <= 1)
        {
            count++;	
        }
    }	
}

	end_time=omp_get_wtime();
    pi = 4 * ((double)count / total_number);
	printf("Spend [%f] seconds\n",end_time-start_time);
    printf("Final result pi = [%f]\n",pi);
    printf("Total tosses is [%ld], Use [%d] threads\n",total_number,thread_num);
    //Just for debug
	char str[30];
    sprintf(str,"result/%ld",total_number);
    SaveResult(str,thread_num,end_time-start_time,pi);
	free(Local_Count);
}

//Just for debug
void SaveResult(char* str,int size,double time,double pi)
{
        FILE* fp = NULL;
        fp = fopen(str,"a+");
        sprintf(str,"%d-%f-%f\n",size,time,pi);
        fputs(str,fp);
        fclose(fp);
}
