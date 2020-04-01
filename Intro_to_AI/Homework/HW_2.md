
# Homework 2

## Chapter 3 Exercises (P 115-1183): 

3.10[12 pts]

state - a collection of remember information that defines the programs interpretation of it's environment

state space-the set of all states reachable from the initial state by any sequence of actions

search tree-a graph starting at the initial state of all possible states spaces

search-the process of looking for a sequence of actions that reaches the goal

node-the individual states in a graph network

goal-the desired outcome of a given set of actions

action-a transition from one state to another

transition model-a description of all possible actions

branching factor-the maximum number of sucessors to any node in a graph

#### 3.11
#### What’s the difference between a world state, a state description, and a search node?

the world state is the environment in which the agent is operating

the state description is the agents atomic representation, or internal model, of that world state

a search node is a unit containing a state, and of achievable states from that state, in a graph. 

Why is this distinction useful?

because the map is not the territory, a givens agents perception of the world state will always be imperfect, due to the computational cost of representation and imperfect sensors, so abstraction is necessary. it doesn't need a perfect understanding of the world, only a finite set of actions connected to each state description, to know the appropriate action to get closer to a given goal, so long as each is properly implemented. 


3.12
An action such as Go(Sibiu) really consists of a long sequence of finer-grained actions:

turn on the car, release the brake, accelerate forward, etc. Having composite actions of this kind reduces the number of steps in a solution sequence, thereby reducing the search time.
Suppose we take this to the logical extreme, by making super-composite actions out of every possible sequence of Go actions. Then every problem instance is solved by a single super-composite action, such as Go(Sibiu)Go(Rimnicu Vilcea)Go(Pitesti)Go(Bucharest). Explain how search would work in this formulation. Is this a practical approach for speeding up

this would work much the same as searching for a minimum value. 
he most appropriate way to build such a mapping is a hash map, but this means that not only would you need to allocate memory for both the all the possible states from each move, but also an overhead in order to avoid collisions when inserting new values. There might be a slight advantage in terms of performance, but this would be offset by an increase in space complexity by orders of magnitude.

3.14[8 pts]
Which of the following are true and which are false? Explain your answers.

a. Depth-first search always expands at least as many nodes as A∗ search with an admissible heuristic.

False, a depth first search would only expand out to nodes at depth d, whereas A* will expand node due to the associated cost. This means that if the goal is near the beginning of a depth first search but there are lower cost nodes along the way, the depth first search will likely find it first. 

b. h(n) = 0 is an admissible heuristic for the 8-puzzle.

True

because the admissible heuristic is 0, the cost are non-negative and thus each piece is moved at least once. h(n) is not an admissible heuristic only if h(n) is equal to the number of tiles in the incorrect place. if the number of incorrect tiles is 0, the game is already won. 

c. A∗ is of no use in robotics because percepts, states, and actions are continuous.

False.

if they are continuos then the cost for each one can be factored into the overall cost of a path. A* plays a prominent rule in robotics 

d. Breadth-first search is complete even if zero step costs are allowed.

True

e. Assume that a rook can move on a chessboard any number of squares in a straight line,
vertically or horizontally, but cannot jump over other pieces. Manhattan distance is an
admissible heuristic for the problem of moving the rook from square A to square B in
the smallest number of moves.

3.16
3.23[20 pts]
Compare the performance of A∗ and RBFS on a set of randomly generated problems in the 8-puzzle (with Manhattan distance) and TSP (with MST—see Exercise 3.33) domains.
Discuss your results. What happens to the performance of RBFS when a small random number is added to the heuristic values in the 8-puzzle domain?



## Chapter 5 [15 pts each] Exercises (P.197-200): 

5.8(a)(b)
5.8
Consider the two-player game described in Figure 5.17.

a. Draw the complete game tree, using the following conventions:

• Write each state as (sA, sB), where sA and sB denote the token locations.

• Put each terminal state in a square box and write its game value in a circle.

• Put loop states (states that already appear on the path to the root) in double square boxes. Since their value is unclear, annotate each with a “?” in a circle.

b. Now mark each node with its backed-up minimax value (also in a circle). Explain how you handled the “?” values and why.

5.9
This problem exercises the basic concepts of game playing, using tic-tac-toe (noughts and crosses) as an example. We define Xn as the number of rows, columns, or diagonals with exactly n X’s and no O’s. Similarly, On is the number of rows, columns, or diagonals
with just n O’s. The utility function assigns +1 to any position with X3 = 1 and −1 to any position with O3 = 1. All other terminal positions have utility 0. For nonterminal positions,
we use a linear evaluation function defined as Eval(s) = 3X2(s)+X1(s)−(3O2(s)+O1(s)).

a. Approximately how many possible games of tic-tac-toe are there?

b. Show the whole game tree starting from an empty board down to depth 2 (i.e., one X and one O on the board), taking symmetry into account.

c. Mark on your tree the evaluations of all the positions at depth 2.

d. Using the minimax algorithm, mark on your tree the backed-up values for the positions at depths 1 and 0, and use those values to choose the best starting move.

e. Circle the nodes at depth 2 that would not be evaluated if alpha–beta pruning were applied, assuming the nodes are generated in the optimal order for alpha–beta pruning.

5.16
This question considers pruning in games with chance nodes. Figure 5.19 shows the complete game tree for a trivial game. Assume that the leaf nodes are to be evaluated in left-to-right order, and that before a leaf node is evaluated, we know nothing about its value—the range of possible values is −∞ to ∞.

a. Copy the figure, mark the value of all the internal nodes, and indicate the best move at the root with an arrow.

b. Given the values of the first six leaves, do we need to evaluate the seventh and eighth leaves? Given the values of the first seven leaves, do we need to evaluate the eighth leaf? Explain your answers.

c. Suppose the leaf node values are known to lie between –2 and 2 inclusive. After the first two leaves are evaluated, what is the value range for the left-hand chance node?

d. Circle all the leaves that need not be evaluated under the assumption in (c).

