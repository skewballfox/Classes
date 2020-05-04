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

#ifdef _OPENMP
#include <omp.h>
#endif
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
    //int tosses_per_thread=total_tosses/threadCount;

    //srand((unsigned)time(NULL));//initialize random seed
    //start timer
    # ifdef _OPENMP
        start=omp_get_wtime();
    # else
        start=time(NULL);
    # endif

    //do the math
    circle_count=toss_darts(threadCount,total_tosses);
    //get finishing time
    # ifdef _OPENMP
        finish=omp_get_wtime();
    # else
        finish=time(NULL);
    # endif
    long double pi=4*((long double)circle_count/(long double)(total_tosses));
                                                              //-((total_tosses%threadCount));
    printf("pfor run\ntoss_dividend: %d\t--\tthreadCount: %d\t--\tpi: %Lf\t--\t",total_toss_dividend,threadCount, pi);
    printf("elapsed time: %lf\n",finish-start);
    return 0;
}

double distance(struct point point_1, struct point point_2){
    /*function: distance
    pupose: calculate the distance between two points
    */
    return sqrt(pow((point_2.x-point_1.x),2)+pow((point_2.y-point_1.y),2));
}

int toss_darts(int threadCount, int tosses)
{   
    /*function: pi_estimator
    purpose: estimate pi using monte carlo method by generating
    random points
    */
    int i;
    int circle_count=0;
    struct point center;
    center.x=1;
    center.y=1;
    struct point dart_toss;
    unsigned int my_seed;
    const double ranged_div = (double)RAND_MAX/2.0;
    #pragma omp parallel for num_threads(threadCount) default(none) \
    shared(tosses,center,ranged_div) private(my_seed,dart_toss) \
    schedule(guided,1024) reduction(+:circle_count)
    for (i=0; i < tosses; i++) {
            dart_toss.x=(double)rand_r(&my_seed)/ranged_div;
            dart_toss.y=(double)rand_r(&my_seed)/ranged_div;
            //printf("%e",dart_toss.x);
            if (distance(center,dart_toss)<1.0) {circle_count++;}
    }
    return circle_count;
}


