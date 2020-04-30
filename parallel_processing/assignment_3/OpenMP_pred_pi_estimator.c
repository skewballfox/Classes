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
#include <omp.h>
struct point{
    double x;
    double y;
};

double distance(struct point,struct point);
int toss_darts(int,int);

int main (int arg_count,char* arg_vector[])
{   
    //necessary variables
    int total_toss_dividend=1;
    if (arg_count>2){total_toss_dividend=atoi(arg_vector[2]);}
    int total_tosses=__INT_MAX__/total_toss_dividend;
    int circle_count;
    double start,finish;
    //necessary omp variables
    int threadCount=strtol(arg_vector[1],NULL,10);
    int tosses_per_thread=total_tosses/threadCount;

    //srand((unsigned)time(NULL));//initialize random seed
    //start timer
    start=time(NULL);

    //do the math
    circle_count=toss_darts(threadCount,tosses_per_thread);
    //get finishing time
    finish=time(NULL);

    long double pi=4*((long double)circle_count/(long double)(total_tosses));
                                                              //-((total_tosses%threadCount));
    printf("circle count: %d\t--\ttotal_tosses: %d\t--\ttosses_per_thread: %d\t--\tthreadCount: %d\t--\tpi: %Lf\t--\t",circle_count,total_tosses,tosses_per_thread,threadCount, pi);
    printf("elapsed time: %lf",finish-start);
    return 0;
}

double distance(struct point point_1, struct point point_2){
    /*function: distance
    pupose: calculate the distance between two points
    */
    return sqrt(pow((point_2.x-point_1.x),2)+pow((point_2.y-point_1.y),2));
}

int toss_darts(int threadCount, int tosses_per_thread)
{   
    /*function: pi_estimator
    purpose: estimate pi using monte carlo method by generating
    random points
    */
    int circle_count=0;
    struct point center;
    center.x=1;
    center.y=1;
    struct point dart_toss;
    unsigned int my_seed;
    const double ranged_div = (double)RAND_MAX/2.0;
    #pragma omp parallel default(none) \
    private(dart_toss,my_seed) shared(ranged_div,tosses_per_thread, center) \
    num_threads(threadCount) reduction(+:circle_count)
    {
        my_seed=(unsigned)time(NULL)+omp_get_thread_num();//initialize random seed        
        for (int i=0; i < tosses_per_thread; i++)
        {
            dart_toss.x=(double)rand_r(&my_seed)/ranged_div;
            dart_toss.y=(double)rand_r(&my_seed)/ranged_div;
            //printf("%e",dart_toss.x);
            if (distance(center,dart_toss)<1.0) {circle_count++;}
        }
    }
    return circle_count;
}
