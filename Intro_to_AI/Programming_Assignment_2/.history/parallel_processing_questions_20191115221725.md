# random questions I had while doing this assignment

1. I'm really interested in observer patterns as a way of autochanging certain values in objects without prompting via  a publisher, is there a way to do this?

2. Is there a way to have functions running in a parallel, for example when doing the testing (calculating the probability a given class given the probability of each pixel), I had the idea to just pass each pixel to every digit in the classifier, one by one, until each had an individual estimate. This would have the advantage of saving space and time. space because this could be a running process given that it's a multiplicative value(each probabiliy multiplied together), and time because otherwise you would have to go through each image 10 times

Not necessarily parallel, but asyncrounous. each builds a 