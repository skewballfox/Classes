import numpy as np   
import math

class digit_class():
    def __init__(self,identifier,training_data_count=5000):
        #self.images=[]
        self.name=identifier
        self.pixel_counts=np.zeros([28,28,2])
        self.image_count=0
        self.posterior_probability=0
    
    def get_image(self, training_data):
        
        self.image_count+=1
        for i in range(28):
            raw_data=training_data.readline()
            for j in range(len(raw_data)):
                if raw_data[j]!='\n':
                    if raw_data[j]!=' ':
                        #image[i,j]=1
                        self.pixel_counts[i,j,1]+=1
                    else:
                        #image[i,j]=0
                        self.pixel_counts[i,j,0]+=1
        #self.images.append(image)
    
    def set_likely_image(self,sample_size):
        self.likely_image=np.zeros([28,28,2])
        for i in range(28):
            for j in range(28):
                    for p in range(2):
                        #NOTE:
                        #Additive smoothing is a type of shrinkage estimator, 
                        # as the resulting estimate will be between the empirical 
                        # estimate xi / N, and the uniform probability 1/d
                        # I'm going to ballpark it and use the priors as k
                        laplace = self.pixel_counts[i,j,p] + self.empirical_frequency
                        smoothing = sample_size + self.empirical_frequency*2
                        self.likely_image[i,j,p] = laplace / smoothing
    
    def set_empirical_frequency(self,sample_size):
        self.empirical_frequency=self.image_count/sample_size
    def finish_training(self,sample_size):
        self.set_empirical_frequency(sample_size)
        self.posterior_probability=np.log(self.empirical_frequency)
        print(self.empirical_frequency)
        self.set_likely_image(sample_size)

    def update_map(self,i,j,pixel):
        """
        NOTE the log of class has already been added to the posterior probability at the end
        of the finish training function 
        """
        self.posterior_probability+=np.log(self.likely_image[i,j,pixel])        

    def get_posterior_probability(self):
        print("posterior probability: ")
        print(self.posterior_probability)
        return self.posterior_probability
    
def train_model(training_data,training_labels):
    digits=[digit_class(digit) for digit in range(10)]
    with open(training_data,'r') as data: 
        with open(training_labels,'r') as labels:
            for k in range(5000):
                label=get_label(labels)
                digits[label].get_image(data)
    for digit in digits:
        #let each digit class know that no more data will be added
        digit.finish_training(5000)
    return digits    

def test_model(test_data,test_labels,digits):
    image_count=1000
    correct_count=0
    with open(test_data,'r') as data: 
        with open(test_labels,'r') as labels:
            for k in range(image_count):
                label=get_label(labels)
                guess=map_classification(data,digits)
                if guess==label:
                    #print("woop")
                    correct_count+=1
    print("got  {} out of {} correct".format(correct_count, image_count))




def map_classification(test_file,digits):
    """

    """
    probs=[]
    print(digits.shape)
    for i in range(28):
        raw_data=test_file.readline()
        for j in range(28):
            if raw_data[j]!=' ':
                pixel=1
            else:
                pixel=0
            for digit in digits:
                digit.update_map(i,j,pixel)
    for digit in digits:
        probs.append(digit.get_posterior_probability())


    return np.argmax([x.get_posterior_probability() for x in digits])

def print_image(image):
    """
    renders binary images in terminal, works with any N*N binary array
    """
    print()
    for i in range(len(image)):
        print()
        for j in range(len(image[i])):
            if image[i,j]==0:
                print(' ',end='')
            else:
                print('#',end='')
    print()

def get_label(label_file):
    """get_label(label_file)
    grabs the next label from a data file
    """
    raw_data=label_file.readline()
    value=int(raw_data)
    return value
            

if __name__=="__main__":
    digits=train_model('digitdata/trainingimages','digitdata/traininglabels')
    test_model('digitdata/testimages','digitdata/traininglabels',digits)