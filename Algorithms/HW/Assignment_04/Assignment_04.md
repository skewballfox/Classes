#Assignment 04

##Question 1

### A:

No its not... so False

Consider the same example listed on page 22 (67 cents)

the greedy algorithm would result in the same number of coins had a twenty piece not been in play:

6 coins

2 quarters, resulting in 17 cents.

1 dime, resulting in 7 cents

1 nickel, resulting in 2 cents

2 pennies, resulting in 0 cents

however with the inclusion of the twenty piece, this could be solved with 5 coins

1 quarter resulting in 42

2 twenty pieces, resulting in 2 cents

2 pennies, resulting in 0 cents

### B:

if we are talking about the coins of set {1,5,10,20,25}
consider 31 cents

which can be either:
20 10 1

or

25 5 1 

### C:

given: coins=[1,5,10,20,25], change=some $x \in \mathbb{Z}$ with change $<=99$, and coins_checked being a subset of the list of coins that have already been accounted for...

the optimal solution would break the problem down into two subproblems, the first being the ways to break down the given change into a smaller amount of coins(think of the change as a x number of pennies), the second is making sure that the amount of coins from the previous subproblem are less than the current amount of coins. if the change passed to the subproblem is 0, then there is no further checking necessary. 

the recurrence relation:

$$ mincoins[change]\left  \{ \begin{array}{l} 0 \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ if change=0\\  min_{i:coins_i\le p} \{1+mincoins[change-coins_i]\}\ \ \ \ \ \ if change>0 \end{array} \right. $$

to express the idea in python (honestly about as readable as pseudocode)

```
def make_change(coins,coins_checked,change):
     if change==0:
         return 0
     smallest_number_of_coins=change

     for i in range(0, coins_checked):
         if coins[i] <= change:
            coins_from_subproblem= make_change(coins, coins_checked, change-coins[i])
                
            if coins_from_subproblem+1<smallest_number_of_coins:
                smallest_number_of_coins=coins_from_subproblem+1
        
     return smallest_number_of_coins
```           

## Question 2

### A

given sequencens $X=[x_1,...,x_i]$ and $Y=[y_1,...,y_j]$, and C the length of longest common substring of both

proof by induction
m=length of X
n=length of Y
i=iterator of X
j=iterator of Y
base case: 
i or j =0

then C[i,j]=0

Consider some C[i,j] and assume that the claim holds for all the previous iterations of i and j
where i'+j'<i+j

if $X_i==Y_j$, then 
  
  C[i,j]=C[i-1,j-1]+1

else
 
 if c[i-1,j] is greater than or equal to c[i,j-1], then
    
    c[i,j] is equal to c[i-1,j]
 
 else
    
    c[i,j] is equal to c[i,j-1]

thus is holds for some i, j 

now consider C[i+1,j+1] 

if $X_{i+1}==Y_{j+1}, then 
  
  C[i+1,j+1]=C[i,j]+1

else
  
  if c[i,j+1] is greater than or equal to c[i+1,j], then
    
    c[i+1,j+1] is equal to c[i,j+1]
  
  else
    
    c[i+1,j+1] is eqal to c[i+1,j]

thus the algorithm holds for all iterations of i,j to the length of the shortest sequence of X and Y

### B

