# Naive Bayesian Classifier Report

## Part 1

The overall proccess of the program is this:

### Meta

1. the data locations, sample size, shape of the data is contained in a file named *metadata.json*
2. logging is set from the beginning and by default only includes what is pertinent to the assignment, more information can be included by passing the debug class when instantiating the model. The logs are stored in a log file with the same name as the program save for the .log extension


### Training:

1. make a list of objects, one for each classification(in this case 0-9)
2. grab the locations of the data as well as some pertinent information about the data in a file named *metadata.json*
3. loop through all the images, and the labels at the same time.  send the images to their respective digit classification
4. update the pixel counts for each classification (stored in a in a numpy array with shape [28,28,2] )
5. after looping through all 5000 digits, set the empirical frequency, followed by the probability of each pixel for that classification

### Testing:

1. same as training up to what happens when I grab the labels
2. instead of sending a copy of the image to each classifier, I send each pixel to the digit objects and have them update the posterior probability given the probability of that pixel containing that value
3. once the image is complete, I gather a list of the posterior probabilities computed from their respective classifications)
4. I then use the index of this posterior probability to determine what classification it belongs to and return it
5. I compare this to the label for the test data.
6. if the guess is correct, I update the correct count, if it isn't I update the confusion matrix
7. regardless of the guess, I update the prototypicals images for the estimated digit

### Evaluation:

1. I log the correct count for the model
2. I log the classification success rates for each digit
3. if a non-zero value I log the output of the confusion matrix for each digit
4. in the process of logging the confusion matrix I grab the 4 most confused pairs of digits
5. I pass these pairs to a plotting function which then plots the digits likelihood along a single axis, as well as their log odds ratios

### Notes on implementation

* for the smoothing constant I used the empirical frequency as the smoothing constant. I was trying to find out more about how the pseudocount is chosen, and most things I came across were opaque at best, so this may have been a rather productive result of a misunderstanding of some of the mathematical representations I saw for additive smoothing. setting the smoothing constant to the frequency of the class seemed to make sense, and led to a fairly accurate model (77.4%)

* for the model score, correct count and confusion rates, please look at the attached logs in the zip file for the most recent run

* for the printed images and log odds, please see the most_confused_images.pdf. sorry for not having more information on there I am still learning how to use matplotlib

* I didn't have a chance to test didn't pixel groupings or feature maps, will explore later on my own. 