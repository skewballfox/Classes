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

struct point{
    double x;
    double y;
};

double distance(struct point,struct point);
int toss_darts(int);

int main (int arg_count,char* arg_vector[])
{   
    //necessary variables
    int total_toss_dividend=1;
    if (arg_count<=2){total_toss_dividend=(int)(arg_vector[1]);}
    int total_tosses=__INT_MAX__/total_toss_dividend;
    int circle_count;
    double start,finish;
    srand((unsigned)time(NULL));//initialize random seed
    //start timer
    GET_TIME(start);

    //do the math
    circle_count=toss_darts;
    //get finishing time
    GET_TIME(finish);

    long double pi=4*((long double)circle_count/(long double)total_tosses);
    printf("serial\t--\tpi: %Lf/t--/t", pi);
    printf("elapsed time: %lf",finish-start);
    return 0;
}

double distance(struct point point_1, struct point point_2){
    /*function: distance
    pupose: calculate the distance between two points
    */
    return sqrt(pow((point_2.x-point_1.x),2)+pow((point_2.y-point_1.y),2));
}

int toss_darts(int max_tosses)
{   
    /*function: pi_estimator
    purpose: estimate pi using monte carlo method by generating
    random points
    */
    int circle_count;
    struct point center;
    center.x=1;
    center.y=1;
    struct point dart_toss;
    for (int i=0; i < max_tosses; i++)
    {
        dart_toss.x=((double)rand()*(2.0)/RAND_MAX);
        dart_toss.y=((double)rand()*(2.0)/RAND_MAX);
        if (distance(center,dart_toss)<1.0) {circle_count++;}
    }
    return circle_count;
}
