# Homework 1

Notes: 

1. Please show steps of your solution to a problem if the process to reach your answer is not apparent. You will receive partial credits for them even your final results are not completely correct. 

2. Please submit a single PDF to the submission link in Canvas

## 1.1 

Define in your own words

 (a) intelligence: the ability to think, to reason about your environment

 (b) artificial intelligence: our attempt at creating something that isn't us that meets the the criteria being considered intelligent
 
 (c) agent: simply a program that acts autonomously, this requires the ability to percieve the environment, and make decisions based on environmental context. 
 
 (d) rationality: to linearly reason based on observation and testing
 
 (e) logical reasoning: to linearly reason based on established premises in order to reach optimal conclusions

# 1.7

To what extent are the following computer systems instances of artificial intelligence:

• Supermarket bar code scanners.

This is more deterministic, where a given input matches a given output. You put in the right key, you get the right value, and as such involves very little reasoning. 

• Voice-activated telephone menus.

the speech is likely converted to text, which is then checked against the available options. IMO, if it requires an exact match, it is not artificially intelligent, if it allows for some ambiguity, where it has to determine the *likely* desired response, then it is artificially intelligent. The underlying speech to text engine may also be artificially intelligent depending on how it derives the text

• Spelling and grammar correction features in Microsoft Word.

spelling

AI in the sense that it's likely looking for an exact match in some dictionary, and then suggesting correction based on nearest neighbors(in terms of letter ordering).

grammar

Definitely AI, as it has to determine each words relation to every other word in a sentence, "understand" abstract concepts like ordering, placement and structure, and then infer when a correction is likely required. 

• Internet routing algorithms that respond dynamically to the state of the network.


1.14
Examine the AI literature to discover whether the following tasks can currently be
solved by computers:

a. Playing a decent game of table tennis (Ping-Pong).

b. Driving in the center of Cairo, Egypt.

c. Driving in Victorville, California.

d. Buying a week’s worth of groceries at the market.

e. Buying a week’s worth of groceries on the Web.

f. Playing a decent game of bridge at a competitive level.

g. Discovering and proving new mathematical theorems.

h. Writing an intentionally funny story.

i. Giving competent legal advice in a specialized area of law.

## 2.4
For each of the following activities, give a PEAS description of the task environment and characterize it in terms of the properties listed in Section 2.3.2.

• Performing a gymnastics floor routine.

Performance Measure: executing the grandest possible move with optimal levels of control

Environment: mat, scorekeepers

Actuators: legs, arms, torso

Sensors: eyes, tactile sensation, sense of proprioception, spatial awareness, depth perception

• Exploring the subsurface oceans of Titan.

Performance Measure: not immediately dying, sending feed before doing so

Environment: very cold, very salinated, likely very corrosive, water. A large layer of ice between the entry point and the destination, space debree on the path to that ocean. our atmosphere, it's atmosphere, solar radiation on the way, alot more thing, generally all terrible

Actuators: rockets to exit our atmosphere, probably some kind of impact resistant shield if we're trying to just yeet it through the surface, or some kind of drill if not, fins or underwater propulsion system if we have any hopes of actually making it to that point, some method of collecting samples for onboard analysis, some meathod of broadcasting data back to earth to see what went wrong

Sensors: very, very hardened camera, some method of analyzing onboard samples

• Playing soccer.

Performance Measure: kicking the ball in the net more than the opposition

Environment: other players, grass, goalie, referee, goal

Actuators: legs and head

Sensors: eyes, ears, tactile sensation, sense of depth perception, sense of proprioception. 

• Shopping for used AI books on the Internet.

Performance Measure:

Environment:

Actuators:

Sensors:

• Practicing tennis against a wall.

Performance Measure: hit the ball as many times as possible

Environment: indoor tennis court, ball, wall

Actuators: racket, legs, arms, 

Sensors: eyes, ears

• Performing a high jump.

Performance Measure: optimal jump height without injury

Environment: track, landing pad , hurdle, pole

Actuators: pole, legs, arms

Sensors: eyes, tactile sensation, sense of proprioception, spatial awareness, depth perception

• Bidding on an item at an auction.

Performance Measure: win bid at minimal cost

Environment: platform, other bidders, allocatable funds

Actuators: fingers or simulated clicks, voice or numerical input

Sensors: eyes and ears or page source, realtime feedback from platform

## 2.5

Define in your own words the following terms

agent: anything which can perceive it's environment through sensors and act on it's environment through actuators. 

agent function: the mathematical description that describes how an agent behaves.

agent program: the algorithm that determines how an agent acts. 


rationality: a set of metrics designed to lead to optimal outcome based on the definition of success, prior knowledge of the environment, available actions and the ability to undestand how chronological relations.

autonomy: the ability of an agent to act independently of it's designer or starting specifications. I suppose it is equivalent to the agents degree of variability from expected behavior. 

reflex agent: memoryless agents, essentially decision making seems to be based on a markov chain. they don't take into account previous states when making decions, so the next action depends on current state of the agent. 

model-based agent: seems to be a step up from a simple reflex agent, where the agent keeps an internal model of it's environment, and it's place in that environment. 

goal-based agent: A reflex agent agent that works toward a given goal, thus at this point it's behavior isn't so much a random walk, as it is a trial and error effort towards some desire outcome. 

utility-based agent: a step up from a goal based agent in that it has some method of determining performance of each action taken toward that goal. 

learning agent: an agent that is able to create rules based off a trial and error approach to optimizing desired outcome. doing so requires feedback for peformance, and something to generate obstacles or problems for it to overcome. 



## 2.8
Consider a simple thermostat that turns on a furnace when the temperature is at least 3 degrees below the setting, and turns off a furnace when the temperature is at least 3 degrees above the setting. Is a thermostat an instance of a simple reflex agent, a model-based reflex, or a goal based agent?

simple reflex based, it's getting a single value from a sensor(temperature, and deciding on an action from that input), it doesn't contain any model of the world, and is thus neither model nor goal based. 



