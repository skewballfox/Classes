#### 1. Find the least integer $n$ such that $f(x)$ is $O(x^n)$  for each of the functions. Follow the definition of big-O notation and also specify the values of the witnesses $C$ and $k$ for each.

#### (a) $f(x) = 3x^3 + log^4x$  

since $log^4x$ is of a lower order than $3x^3$ we will drop that from the equation

with $O(x^n)=g(x)$, there exist some constant $C$ and some starting point $k$, such that so long as $x\ge k$, $f(x)\le C * g(x))$

since the function F is a third degree polynomial then n has to be at least a third degree polynomial to always be larger. 

since the constant in that polynomial is 3, the smallest constant that satisfies the constraint $f(x)\le C * g(x))$ for all possible values of $x$ greater than $k$ is 4
in this case $n=3,C=4,k=1$

#### (b) $f(x) = (x^4 + x^2 + 1) / (x^3 + 1)$ 

this can be simplified to:
$\frac{(x^2 + x + 1)(x^2 - x + 1)}{(x+1)(x^2-x+1)}$

$\frac{(x^2 + x + 1)}{(x+1)}$







with $O(x^n)=g(x)$, there exist some constant $C$ and some starting point $k$, such that so long as $x\ge k$, $f(x)\le C * g(x))$

since the equation is a second order polynomial over a first, the lowest value of $n$ is 1. 


since this function would be fractional at $x=1$ we can assume that the lowest value for k is $1$

if we only consider the highest order polynomials $C$ only has to be 1, when considering the rest it should be 2 to make up for the fractional value as $x$ 

in this case $n=1,C=2,k=1$

#### 1. Given array $A[1..12] = [7, 6, 12, 9, 3, 4, 5, 11, 9, 14, 2, 8]$ 


#### a) Show the value of array A and value of local variables (i, j, key) in pseudo-code of insertion sort (refer to slide 5 of lecture2_insertion_sort.pdf when performing insertion sort from the leftmost element (7) to the 6th element (4). Note that you need show the value of A and local variables after initialization and after each iteration step of $for$ loop (see example of slide 12).

```
loop 1
key: 6
7 and 6 swapped
[ 6 7 12 9 3 4 5 11 9 14 2 8 ]
loop 2
key: 12
loop 3
key: 9
12 and 9 swapped
[ 6 7 9 12 3 4 5 11 9 14 2 8 ]
loop 4
key: 3
12 and 3 swapped
[ 6 7 9 3 12 4 5 11 9 14 2 8 ]
9 and 3 swapped
[ 6 7 3 9 12 4 5 11 9 14 2 8 ]
7 and 3 swapped
[ 6 3 7 9 12 4 5 11 9 14 2 8 ]
6 and 3 swapped
[ 3 6 7 9 12 4 5 11 9 14 2 8 ]
loop 5
key: 4
12 and 4 swapped
[ 3 6 7 9 4 12 5 11 9 14 2 8 ]

```



#### 3. Comparisons in insertion sort

a) What are the number of comparisons in best case and worst case when performing insertion sort on an array of N elements. Give your explanation of your answers.

best case is the array is already sorted in this case the number of comparisons is n and thus the time complexity is O(n)

in the worst case (the array is in descending order), each comparison has to be made twice, giving this a time complexity of $O(n^2)$



b) Design a program that perform insertion sort (ascending order) on an input array and print the total number of comparisons that have been made. You can implement by using any programming languages such as C/C++, Java, Python, etc. For example, in the case of array $B=[30,10, 20, 40]$, there are 4 needed comparisons to perform insertion sort (30 vs 10, 20 vs 30, 20 vs 10, and 40 vs 30).

this is actually the same as the program I designed for 2