/* Purpose: serial implementation of floyd's algorithm. finds all the shortest path from
 * every node to every other node.
 * 
 * Author: Joshua Ferguson
 */
#include <math.h>

struct node{
    int name;
    struct edge* adjacent; 
};
struct edge{
    int weight;
    struct node* destination;
};

node* generate_graph(const int);


