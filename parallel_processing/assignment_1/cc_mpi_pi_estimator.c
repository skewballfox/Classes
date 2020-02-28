/*
    Purpose: calculate pi in parallel with collective communication
    using monte carlo method by simulating dart tosses at a 2x2
    square containing a dart board with radius 1

    command-line aguments:
      1) number_of_processes
      2) value to divide total iteration count 
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
int toss_darts(int);

int main (int arg_count, char* arg_vector[])
{
    //necessary variables
    int number_of_processes;
    int process_rank;
    int local_circle_count, total_circle_count;
    double start,finish,local_elapsed,elapsed;
    //initializations 
    MPI_Init(&arg_count,&arg_vector);
    MPI_Comm_rank(MPI_COMM_WORLD, &process_rank);
    MPI_Comm_size(MPI_COMM_WORLD, &number_of_processes);
    
    srand((unsigned)time(NULL));//initialize random seed
    
    //figure out task
    int total_toss_dividend=1;
    if (arg_count<=2){total_toss_dividend=atoi(arg_vector[1]);}
    int total_tosses=__INT_MAX__/total_toss_dividend;   
    int local_tosses = total_tosses / number_of_processes; 
    //start timer
    MPI_Barrier(MPI_COMM_WORLD);
    start=MPI_Wtime();
    local_circle_count = toss_darts(local_tosses);    
    MPI_Reduce(&local_circle_count,&total_circle_count,1,MPI_INT,MPI_SUM,0,MPI_COMM_WORLD);
    finish=MPI_Wtime();
    local_elapsed=finish-start;
    MPI_Reduce(&local_elapsed,&elapsed,1,MPI_DOUBLE,MPI_MAX,0,MPI_COMM_WORLD);  
    if (process_rank == 0){       
        long double pi = 4 * ((long double)total_circle_count / (long double)total_tosses);
        printf("processors: %d\t--\tpi: %Lf\t--\t",number_of_processes, pi);
        printf("elapsed time:%lf\n", elapsed);
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
