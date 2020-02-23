/*
    Purpose: calculate pi serially using monte carlo method
    by simulating dart tosses at a 2x2 square containing a
    dart board with radius 1
    
    Author: Joshua Fergsuon
*/
#include <time.h>
#include <stdlib.h>
#include <math.h>
#include <stdio.h>
#include <mpi.h>

struct point{
    double x;
    double y;
};

double distance(struct point,struct point);
long double pi_estimator(int);

int main (int arg_count, char* arg_vector[])
{
    //necessary variables
    int number_of_processes;
    int process_rank;
   
    //initializations 
    MPI_Init(&arg_count,&arg_vector);
    MPI_Comm_rank(MPI_COMM_WORLD, &process_rank);
    MPI_Comm_size(MPI_COMM_WORLD, &number_of_processes);
    
    srand((unsigned)time(NULL));//initialize random seed
    //figure out task
    int max_tosses = __INT_MAX__;   
    int local_tosses = max_tosses / number_of_processes; 

    int local_circle_count = toss_darts(local_tosses);    
    if (process_rank != 0) {
        MPI_Send(&circle_count,1,MPI_INT,0,0,MPI_COMM_WORLD);
        
    } else {
      
        int total_circle_count=local_circle_count;
        
        for (source=1; source<number_of_processes;source++)
        {
            MPI_Recieve(&local_tosses,1,MPI_INT,source,0,MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            total_circle_count+=local_tosses;
        }   
        long double pi = 4 * ((long double)total_circle_count / (long double)max_tosses);
        printf("pi is %Lf", pi);
    }

    MPI_Finalize();
    return 0;
}

double distance(struct point point_1, struct point point_2){
    /*function: distance
    pupose: calculate the distance between two points
    */
    return sqrt(pow((point_2.x-point_1.x),2)+pow((point_2.y-point_1.y),2));
}

int toss_darts(int number_of_tosses)
{   
    /*function: toss_darts
    purpose: simulate tosses at a dart board by generating
    random points
    */
    int circle_count;
    struct point center;
    center.x=1;
    center.y=1;
    struct point dart_toss;
    //implement mpi_send and mpi_recieve
    for (int i=0; i < number_of_tosses; i++)
    {
        dart_toss.x=((double)rand()*(2.0)/RAND_MAX);
        dart_toss.y=((double)rand()*(2.0)/RAND_MAX);
        if (distance(center,dart_toss)<1.0) {circle_count++;}
    }
    return circle_count;
}
