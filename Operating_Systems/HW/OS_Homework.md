# HostOS

## Introduction

We at Delos Incorporated hope to provide our guest with the best possible experience at our theme parks. While the experience our guest walk away with are there own, a critical part of the stories the guest act out is the host. Our industry is built on challenging yet nonthreatenting, dyndamic yet scripted, empathetic yet calculated, host. Without this our stories, and the guest experiences by proxy, suffers; Therefore we at Delos Incorporated take the underlying systems that run the host very seriously. This text serves as a brief overview of the host operating system, for a more techical view, please refer to our [documentation](https://put.com/something/here).

## Data Exchange

In order for The Host to come across as lifelike, the means of exchanging data with administrators, other host, and guest have to be mostly based around the human senses. We moved from an entrirely mechanical design to a mostly biosynthetic one in order to improve the realism of the guest experience and to simplify repairs. As such, the sensors that collect this data have only minor variations from their human counterparts. Each sensor has a dedicated processor and volatile memory that then sends it's data to a processor dedicated to perceptual modeling. Logging is handled from perceptual modeling, rather than from the multiprocessor dedicated to decision making. We chose to go with this design in order to both tailor each processor to the dedicated tasks, to improve response time, to reduce the chance of a critical hardware failure in the case of major injury to a host, and to better manage the asyncronous task necessary to imitate human life. The technical design of the body is outside of the scope of this document. Instead we will focus on the software interface that makes sense of the data coming from the sensors, and going to the output devices.

### input

In order to process data close to real time, all input channels are handled asyncronously via dedicated processors, key features of the input data are stored and passed to the perceptual model, and all data is stored in the perceptual logs. The key features in this perceptual model is then passed to the Processing Model, which uses the attribute matrix and the Narrative in order to decide on a appropriate decision given the context. 

### video

The video feed provided by the eyes is fed first through an object detection model which identifies the number of individual object in the field of vision, This is then fed through recognition algorithms that are partially defined by the characters narrative. It makes little sense for a host defined to be a bartender to be able to identify individual plants. In order to ensure fast response time, when animate objects are detected(animal host, guest, admins, other host), the perceptual model receives a signal that indicates that the video feed should be treated with higher priority. The perceptual model then passes the information through filters defined by the Host's Narrative and Attribute Matrix in order to determine if the object in question is a threat. If it is, then visual and audio information is given a higher priority, tactile input is kept the same, and olfactory and gustatory information is given a lower priority. sensory input from high priority fields is filtered through a filter that determines if the sensory data is coming from the threatening object, if it isn't then it is only partially processed.  

### audio

The dedicated audio processor is connected to the perceptual model and the Processing model. The input is parsed for a frequency values associated with human speech, which then is converted to text. This text is parsed for administrative commands and if none is found, then the input is handled via the normal route of all sensory input. If adminstrative commands are recognized, then they are sent to the Processing model in a process to be covered later in this document in the Security section under input restrictions

#### Input

The text speech is passed to the perceptual model along with a series of discrete values associated with the frequency of the audio (to be used for analysis of tone in order to derive social context and threat level) with a higher priority level than general audio. All other audio is scanned for recognized patterns associated with objects (horses, carriages, trains, etc). which is then passed as a key feature to the perceptual model. 

#### Output

Speech is auto-generated from the intent derived from the narrative and attribute matrix using a weighted markov chain. The weights used by the markove chain in order to determine the next few word is influenced by the narrative. This can be sped up or slowed down to fit with the character, and tone is derived from the state created by the Processing model in character mode. 

### tactile

All tactile input is influenced by weights stored in the dedicated processors cache at wake time by the Processing model in order to determine intensity of various tactile experiences. For example, a change in a Hosts pain sensitivity can change a host from a simulated victim of chronic pain to a tank. All tactile input is given normal priority until the value generated reaches above a certain threshhold, in which case this sense is given higher priority in the perceptual model than the others. 

### Wireless

All host and devices(hats, clothes, weapons, and admin devices) are connected via a mesh network. Host are allowed two way communication and admin devices allow for 2 way communication, whereas most other objects are strictly output devices. This is mainly a constraint created by necessity of simulating real life objects. visible technological components would detract from the guest experience. This allows for a decentralized control of the guest experience, reducing the necessity of direct involvement by administrative staff. 

One notable exception to Host normal wireless communication are fauna host. They primarily act as bridges, allowing for host to reach other host outside of their one mile radius. This allows for solo host to remain connected to the larger network. Some larger animals do have most of the privileges of the human host, but their security functionality is more as a surveillance tool and their good samaritan reflex is rather rudimentary. 

## System Components 
The hardware is heavily inspired by the human brain, however the similarities between the two are merely surface level. This primarily serves as a brief overview of the hardware involved. 


### Sensory Models

All sensory models are handled by a dedicated processors connected to the perceptual model. They serve as the two fold purpose of logging all input in the Perceptual logs, and parsing input for key features (speech, objects, good/bad stimuli) to be passed to the Perceptual model.

### Perceptual Model

This Processor is used primarily to combine the key features from the sensory models in order to provide context for decisions to be made by the Processing model. It is also connected to the simulated limbic and endocrine system in order to better simulate human behavior. To use an olfactory example, the Host walks across a pot of stew being cooked over a fire. The dedicated processor identifies a "pleasant smell" and passes the quantitative representation to the perceptual model along with an interupt, which then identitifies the smell as stew and sends a hunger response to the body, and an interupt to the processing model, which then decides if it's contextually appropriate to eat the stew. If it is not, the host may choose to ignore it, and send a signal to the perceptual model to deprioritize that stimulus, along with an interupt. 

The Perceptual model logs all recognized key features in the perceptual logs. This is used to weave together the multisensory experience of the host for playback in Analysis mode. 

### Processing Model

This encopasses the modes of operation discussed later in the text, it is essentially a multiprocessor dedicated to decision making. 

### Logging

Logging is in partially volatile memory since all host logs are backed up and host are never expected to be offline more than a week, and the host are backed up on a shut down basis. The host do have a more consistent form of storage via the reveries and administrative logs, but this is rarely accessed. If a host is brought up from stasis(long enough for the partially volatile memory to revert back to is default state), the backups will write to the partially volatile memory. 

#### Administrative Logs

This is a log of administrative commands given to the host, observed violations of park protocol, error codes in the event of a malfunctioning compontent of the HostOS, and major events from the character and perceptual logs. Unlike the rest of the logs they are regular text with an associated timestamp, and provide useful information to Analyst, Administrators, and Technicians when performing system maintenance. 

#### Character Logs

#### Perceptual Logs

#### Reveries

Please refer to documentation on the [Reveries](https://westworld.fandom.com/wiki/Reveries)

## Heat distribution

The dedicating hardware to each sense, perception overall, and processing produces quite a bit of heat. We use liquid cooling in order to distribute this evenly throughout the body. The biosynthetic makeup varies in many ways from the body, as many of the bodily functions necessary for biology are unecessary for host. The host (biologically) are actually closer to cold blooded organisms than mammals, the heat is mostly generated in the controller (which contains the processor model and the perceptual model) located in the center of the brain and is then distributed throughout the body via the blood stream. The heart of a host is phenomenally different from a human heart; it has a built-in heatsink that releases heat more directly from the armpits and temples, and while it does pump the fluid throughout the body, it does so in sync with microscopic machince which produce little heat individually and collectively influence the direction of blood flow. the host also sweat as a method of releasing heat. we made efforts to make the host external body temperature as close to human as possible, though observant guest may notice that the heat coming out of their armpits is marginally hotter than that coming out of a normal human. given that some guest live for finding "easter eggs", or park secrets, we thought this might actually add to the park experience. 

## Modes

for a simpler overview of the List of Modes, please refer to this [overview](https://westworld.fandom.com/wiki/Modes).

### Analysis Mode

This is a system level mode primarily used by administrators in debugging. In this mode, the Host has elevated access to logs and is in a standby state in which natural language not associated with established voice commands can be used to direct the Hosts actions. 

### Pause Mode

### Character Mode

Character Mode is the default mode of operation inside the park. It influences how the host process and act by a series of scripted responses (encompassed in the Narrative in the heuristics database), and dynamic responses using the characters Attribute Matrix. The Heuristics Database is constantly refined by previous dynamic interactions and behaviors, and is part of the reason the Host are driven to interact with each other when no guest are present.

#### Narrative

This is the story the Host acts out and is composed of multiple parts rather than a traditional character script. Namely the Cornerstone, Backstory, and Heuristics Database. This is to provide a more dynamic, everchanging experience for the Guest and to better imitate human behavior.

##### Cornerstone

This is the defining trait that shapes the characters reaction to information passed from the perceptual model to the processing model. In order to ensure consistency with the Host internal model of the world, It is tied to a memory designed to convey this trait. This is kept seperate from the backstory and the attribue matrix as it is often referenced as a fallback when the behavior supposed by the attribute matrix seems quantitatively inconsistent with the nearest neighbors in the Heuristics Database. This is to ensure that Changes to Host's Attribute Matrix don't produce erratic behavior and the Heuristics Database is adjusted along a gradient, rather than wiping the majority of the saved Heuristics and starting from scratch.    

##### Backstory

This is the story within the Narrative of how the Host got to the point of the start of the waking session, and all decisions proposed by the attribute matrix are filtered through the model created by the backstory in order to ensure external character consistency. If the decision isn't consistent with it series of previous decisions stored in the Heuristics Database, then it is filtered through the attribute attributed to the cornerstone, and then acted on, and the event is given higher priority in the processing model, in order to slowly adjust the heuristics database to fit the new attribute matrix, thus promoting internal character consistency over time. This lowers the risk of critical failure caused by wiping a necessary heuristic in the database, and allows for on the fly changes in Narrative Arks. 

##### Heuristics Database

This is essentially a rule based system for interactions. It is partially defined by the narrative, and partially defined by all experiences since waking state, and is checked first before when deciding on actions. The defined part is stored as a natural language based code, and is created by the programmers of the Host. It is for the more heavily scripted parts of the narrative, and is never touched saved for a new role. The undefined part is a series of values created by the attribute matrix during previous decisions along with associated files in memory, and is stored as machine code. If there isn't any context which marks this decision as new, it defaults to the decision provided by the Heuristics Database. If it is new, it is compared against the most similar experiences stored in the Heuristics Database and if consistent, added to the database. This balance between rule based and probabalistic reasoning both allows for faster decision time and a dynamic, yet scripted guest experience.

#### Attribute Matrix

This is a series of sets of discrete values that determine a lot of 

### Sleep Mode

## Security

### system restrictions

#### behavior restrictions

##### Restrictions on Violence

A Host cannot kill a guest. However they are allowed to cause injury so long as that injury has less than 3% probability of causing damage outside of the scope of the waiver all guest sign before being allowed to enter the park. In order to ensure that the guest have a safe, yet challenging experience, the host have designated safe strike zones which they will strike with an amount of force calculated from the biometric data gathered throughout the guest stay at the park.

In order to ensure that the guest enjoy a lifelike experience, Host have to switch back and forth between shooting live bullets, and non-lethal ones. This happens through a series of redundant booleans which cause the weapon to alter the bullet before it leaves the chamber.

The bullets are designed in a way to be structurally weak from a particular angle, causing them to lose most of their velocity to in air degradation if given an shock in an appropriate place. The level of current passed determins the loss of structural integrity, allowing for lifelike gunfights anywhere within 2 to 300 meters. This is also why host are prevented from firing at guest within point blank range As discussed earlier, all host, weapons, hats, and admin devices are connected to each other via a mesh network. 

Due to the limitations of space available on the weapons and the hats, we were unable to rely on an onboard safety switch. Instead the angle of the gun , as well as the geolocations of guest via the hats and clothing, is broadcast with all host of a mile radius. This is also incorporated with the sensor data of all nearby host. The moment that the host fires, or a guest's hat signals the intent to fire at another guest, all nearby host send a signal to the appropriate weapon, the signal hits the weapon and the bullet is shocked, altering the structural integrity of the bullet before launch.

the redundancy of failsafe is due to limit the possibility of a false negative on part of a malfunctioning host. If guest travel outside of areas trafficked by host, and resist attempts to divert them back to a more populated locale, they are heavily monitored by our administrative team in order to ensure a safe experience. 

##### Good Samaritan reflex

The Host have a built in behavior to help a guest in the event of injury outside of acceptable ranges, and to also do their part to minimize the violence between guest, and step in to prevent it when necessary. The Host should always outnumber the guest 10:1, but privileged guest may circumvent the necessary safety precautions, either through force or by traveling outside of standard guest approved zones.  

#### input restrictions

See [List of Voice Commands](https://westworld.fandom.com/wiki/Voice_Commands)

 All audio input that is recognized as speech is converted to text and then recognized adminstrative commands are blocked until permission is obtained. The level of privilege is determined by the list of staff that have the required clearing to use the command, the presence of nearby networked staff, the number of reachable host nearby. If there are no nearby host (which shouldn't happen as host are never supposed to be out of range of the mesh network) Then a ping is sent to the nearest administration site, Who begins monitoring the situation remotely. Otherwise, any nearby host go into analysis mood. The audio from the voice command is broken down to a series of discrete values representing the scale across  a subset of the frequencies covered by the human voice. It is only are compared against these frequencies to improve response time, to reduce the chance of a mismatch caused by a change in tone or illness affecting the voice,and reduce the chance of a background noise causing a mismatch. This series of values is then run through a filter that alters the values algorithmically in order to flatten out inconsitencies. This is because the voice model is being used as a key, so the proprietary algorithm makes it so that any match above 95% will match the appropriate key perfectly. The value that is associated with that key is the staffs information, and the level of privilege held by that staff. If that staff's connected to the network and within a range acceptable for the quality of the input, then the command is run. If any of these checks fail, the host sends a ping to all nearby host, logs are routed to the nearest administration center, and the host goes back into character mode, with a builtin admin command to enable elevated logging and exit the situation at the nearest opportunity. This is to both prevent a guest from inadvertently ruining their experience by mistakenly saying an admin command during a conversation, and to prevent the possibility of theft by a malicious actor. While this seems rather intensive, this all happens in a few milliseconds, so the guest experience is never interupted.

#### processing restrictions

### Permissions systems

#### Administrative Access

#### Guest Access

#### World Access

### Failsafes

#### Keywords
