# Algorithms Class 3

## Pigeonhole principle:

if n+1 pigeons are nested into n holes then at least one hole would contain at least 2 pigeons

### proof suppose the contrary, if each hole contains at most 1 pigeon, then the tolal number of pigeons would be <= 1*n = n 

## stronger form of PHP 

If n*m+1 pigeons are roosted in m holes, then at least one hole would countain at least n-1 pigeons 

### Theorem

In any group of 6, there must exist 3 mutual friends of 3 complete strangers

### Proof 

Use of the PHP, consider the group {A,B,C,D,E,F} and divide {B,C,D,E,F} into 2 groups

friends of A: B,C,D  Cause any two of B,C,D are friends, say  B and C then A, B, C are mutual friends

Strangers to A: E, F

### Notes

Just because B and C are friends to A does not mean that B and C 

## HW Assignment

Complete the 3 friends Theorem by showing the statement is also true in Case A has >= strangers

## Tower of Hannoi (n,A,C,B)

if n=1, then {

    move directly from A to C 
}

else {
    
    ToH(n-1, A,B,C):

    move the longest disk from A to C:
    
    ToH(n-1, B, C, A)
}

## Mathematical Induction

To prove S(n) is true for all n $\in$ N

### Steps:

#### 1) basis: 

prove s(1) is true

#### 2) induction hypothesis:

Assume s(k) is true

#### 3) Induction:

Prove s(k+1) is true. 

### Example of mathematical Induction

$\sum^{n}_{i=1} i = \frac{n(n+1)}{2}$

#### Proof by Induction

##### Basis for n=1
 $\sum^{n}_{i=1} i = 1 = \frac{1(1+1)}{2}$ TRUE

##### hypothesis 

Suppose $\sum^{k}_{i=1} i = \frac{k(k+1)}{2}$

##### induction

$\sum^{k+1}_{i=1} i +(k+1)$

$\frac{k(k+1)}{2}+(k+1)$

$\frac{(k+1)(k+2)}{2}$

