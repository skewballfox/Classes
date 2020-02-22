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
long double pi_estimator();
int main ()
{
    srand((unsigned)time(NULL));//initialize random seed
    int max_tosses=__INT_MAX__;
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
    long double pi=4*((long double)(circle_count)/(long double)(max_tosses));
    printf("pi is %Lf", pi);

    return 0;
}

double distance(struct point point_1, struct point point_2){
    /*function: distance
    pupose: calculate the distance between two points
    */
    return sqrt(pow((point_2.x-point_1.x),2)+pow((point_2.y-point_1.y),2));
}

