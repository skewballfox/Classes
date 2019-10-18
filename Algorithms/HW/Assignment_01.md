#### 1. Prove this theorem: Among any six people, there exists a group of 3 mutual friends or a group of 3 mutual strangers. (Here friends and strangers are considered symmetric relations, i.e., if A is a friend of B, then B is a friend of A; likewise, if A is a stranger to B, then B is a stranger to A).

 consider the group {A,B,C,D,E,F} and divide {B,C,D,E,F} into 2 groups: friends of A, and strangers to A.

 a more general form of the pigeonhole principle states that if $nm+1$ objects are contained in $m$ mutually exclusive sets, then at least one set would countain at least $n+1$ objects. in other words, if there are more objects than there are sets,and an object cannot be in more than one at a time, then one set has to contain more than one object. 
 
 In this case there are $2$ sets available, Friends of A and Strangers of A. there are 5 people who can fall into either set. If you divide the groups evenly and place the last member arbituarily, then one set will have 3 members and the other will have 2. 

 If you do not divide them evenly then one set will have more than 3 by default. 

 therefore in any group of 6 people you will have either 3 mutual friends or 3 mutual strangers. 




#### 2. Prove by induction on $n$ that:

$$\sum^n_{i=0}i^3 = (\sum^n_{i=0}i)^2$$

1. let $S(0)$:

$$\sum^{0}_{i=0}i^3 = (\sum^0_{i=0}i)^2$$
$$0=0$$

Assume $S(k)$ is True:
$$\sum^k_{i=0}i^3=(\sum^k_{i=0}i)^2$$
the right half of the equation becomes:

$$\sum^k_{i=0}i^3=\frac{k^2(k+1)^2}{4}$$

the left half of the equation becomes:
$$(\sum^k_{i=0}i)^2=(\frac{k(k+1)}{2})^2=\frac{(k(k+1))^2}{4}=\frac{(k(k+1)\cdot k(k+1))}{4} = \frac{k^2(k+1)^2}{4}$$

the end result on both sides is equivalent:
$$\frac{k^2(k+1)^2}{4}=\frac{k^2(k+1)^2}{4}$$

therefore, $S(k)$ is true

to prove $S(k+1)$:
the right half of the equation becomes:
$$(\sum^k_{i=0}i^3)+(k+1)=\frac{k^2(k+1)^2}{4}+(k+1)=\frac{k^2(k+1)^2+4(k+1)}{4}$$

the left half of the equation becomes:
$$(\sum^k_{i=0}i)^2+(k+1)=(\frac{(k+1)((k+1)+1)}{2})^2)=\frac{(k+1)((k+1)+1)}{2})^2)=\frac{(k(k+1)\cdot k(k+1))}{4}+(k+1) = \frac{k^2(k+1)^2+(4k+4)}{4}$$

$$\frac{k^2(k+1)^2+(4k+4)}{4}=\frac{k^2(k+1)^2+(4k+4)}{4}$$

therefore we can assume that:
$$\sum^n_{i=0}i^3 = (\sum^n_{i=0}i)^2$$
is true for all cases

#### 3. What is wrong with the following inductive "proof" that all elements in any set must be identical? 
    
For sets with one element the statement is trivially true. 

Assume  the statement is true for sets with $n-1$ elements, and consider a set  $S$ with  $n$  elements.

Let  $a$ a be an element of $S$. 

Write  $S = S_1 \cup S_2$, where  $S_1$ and  $S_2$ each have $n-1$ elements, and each contains  $a$. 

By the inductive hypothesis all elements in  $S_1$ are identical to  $a$ and similarly all elements in  $S_2$ are identical to  $a$. Thus all elements in  $S$ are identical to $a$.

A) this is impossible given axiomatic set theory
2)They never demonstrated that the elements were identical, only stated that $S$ was a superset of $S_1$ and $S_2$. 

I suppose what they were trying to state was that if $S$ is a superset of $S_1$ and $S_2$ and both them contained $a$ then $S$ must have duplicates, and by some ungodly fault of reasoning concluded that meant all the members of both sets were identical to $a$. 